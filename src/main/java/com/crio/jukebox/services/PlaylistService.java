package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.entities.UserPlaylists;
import com.crio.jukebox.exceptions.EmptyPlaylistException;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundInActivePlaylistException;
import com.crio.jukebox.exceptions.SongNotFoundInPlaylistException;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;

public class PlaylistService implements IPlaylistService{
    private final IPlaylistRepository playlistRepository;
    private final IUserRepository userRepository;
    private final ISongRepository songRepository;
   
    public PlaylistService(IPlaylistRepository playlistRepository,IUserRepository userRepository,ISongRepository songRepository) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }


    @Override
    public Playlist createPlaylist(String userId, String playListName, List<String> songIds) {
        // TODO Auto-generated method stub
        User user = userRepository.getUserById(userId);
        List<Playlist> userSpecificPlaylist = user.getPlaylistByUsers(user);
        if(userSpecificPlaylist==null){
            checkSongExistInPool(songIds);
            userSpecificPlaylist = new ArrayList<Playlist>();
            Playlist newPlaylist = playlistRepository.save(new Playlist("1",playListName, songIds));
            userSpecificPlaylist.add(newPlaylist);
            UserPlaylists userPlaylists = new UserPlaylists();
            userPlaylists.addUserPlaylist(user,userSpecificPlaylist);
            user.setUserPlaylists(userPlaylists);
            return newPlaylist;
        }
        Playlist lastPlaylist = userSpecificPlaylist.get(userSpecificPlaylist.size()-1);
        String lastPlaylistId = lastPlaylist.getId();
        checkSongExistInPool(songIds);
        Playlist newPlaylist = playlistRepository.save(new Playlist((Integer.parseInt(lastPlaylistId)+1)+"",playListName, songIds));
        userSpecificPlaylist.add(newPlaylist);
        UserPlaylists userPlaylists = user.getUserPlaylists();
        userPlaylists.addUserPlaylist(user,userSpecificPlaylist);
        return newPlaylist;
    }


    private void checkSongExistInPool(List<String> songIds) {
        for(String id : songIds){
            if(!songRepository.existsById(id)){
                throw new SongNotFoundException("Some Requested Songs Not Available. Please try again."); 
            }
        }
    }
    @Override
    public void deletePlaylist(String userId,String playlistId){
        User user = userRepository.getUserById(userId);
        UserPlaylists userPlaylists = user.getUserPlaylists();
        if(!userPlaylists.checkIfPlaylistExist(user, playlistId)){
            throw new PlaylistNotFoundException("Some Requested Songs Not Available. Please try again."); 
        }
        userPlaylists.deletePlaylist(user, playlistId);
    }
    @Override
    public Playlist addSongInPlaylistCommand(String userId, String playlistId,List<String> songIds){
        User user = userRepository.getUserById(userId);
        UserPlaylists userPlaylists = user.getUserPlaylists();
        Playlist playlist = userPlaylists.findPlayListById(user, playlistId);
        List<String> songsInPlaylist = playlist.getSongsInPlaylist();
        checkSongExistInPool(songsInPlaylist);//Some Requested Songs Not Available. Please try again.
        for(String newSongid : songIds){
            if(checkIfSongAlreadyExistInPlaylist(songsInPlaylist,newSongid)){
                continue;
            }
            else{
                playlist.addSongsInPlaylist(newSongid);
            }
        }
        return playlist;
    }
    @Override
    public Playlist deleteSongInPlaylistCommand(String userId, String playlistId,List<String> songIds){
        User user = userRepository.getUserById(userId);
        UserPlaylists userPlaylists = user.getUserPlaylists();
        Playlist playlist = userPlaylists.findPlayListById(user, playlistId);
        List<String> songsInPlaylist = playlist.getSongsInPlaylist();
        for(String newSongid : songIds){
            if(!checkIfSongAlreadyExistInPlaylist(songsInPlaylist,newSongid)){
                throw new SongNotFoundInPlaylistException("Some Requested Songs for Deletion are not present in the playlist. Please try again."); 
            }
            else{
                playlist.removeSongsInPlayList(newSongid);
            }
        }
        return playlist;
    }
    public Song playPlaylist(String userId, String playlistId){
        User user = userRepository.getUserById(userId);
        UserPlaylists userPlaylists = user.getUserPlaylists();
        Playlist playlist = userPlaylists.findPlayListById(user, playlistId);
        userPlaylists.setActivePlayList(playlist);
        if(playlist.getSongsInPlaylist().size()==0){
            throw new EmptyPlaylistException("Playlist is empty."); 
        }
        String currentSongId = playlist.getCurrentSongId();
        Song currentSong = songRepository.findSongById(currentSongId);
        
        return currentSong;
    }
    public Song playSong(String userId,String param){
        User user = userRepository.getUserById(userId);
        UserPlaylists userPlaylists = user.getUserPlaylists();
        Playlist activePlaylist = userPlaylists.getActivePlayList();
        String currentSongId = activePlaylist.getCurrentSongId();
        List<String> songsInActivePlaylist = activePlaylist.getSongsInPlaylist();
        int sizeOfActivePlaylist = songsInActivePlaylist.size();
        if(param.equals("BACK")){
            int i = getCurrentSongIndexInActivePlaylist(currentSongId, songsInActivePlaylist);
            if(i-1<0) i=sizeOfActivePlaylist;
            String previousSongId = songsInActivePlaylist.get((i-1)%sizeOfActivePlaylist);
            Song currentSong = songRepository.findSongById(previousSongId);
            activePlaylist.setCurrentSongId(previousSongId);
            return currentSong;
        }
        else if(param.equals("NEXT")){
            int i = getCurrentSongIndexInActivePlaylist(currentSongId, songsInActivePlaylist);
            String nextSongId = songsInActivePlaylist.get((i+1)%sizeOfActivePlaylist);
            Song currentSong = songRepository.findSongById(nextSongId);
            activePlaylist.setCurrentSongId(nextSongId);
            return currentSong;
        }
        else{
            if(!checkIfSongAlreadyExistInPlaylist(songsInActivePlaylist,param)){
                throw new SongNotFoundInActivePlaylistException("Given song id is not a part of the active playlist"); 
                //System.out.println("Given song id is not a part of the active playlist");
            }
            Song currentSong = songRepository.findSongById(param);
            activePlaylist.setCurrentSongId(param);
            return currentSong;
        }
        
    }


    private int getCurrentSongIndexInActivePlaylist(String currentSongId, List<String> songsInActivePlaylist) {
        int i;
        for(i=0;i<songsInActivePlaylist.size();i++){
            if(songsInActivePlaylist.get(i).equals(currentSongId)){
                break;
            }
        }
        return i;
    }
    private boolean checkIfSongAlreadyExistInPlaylist(List<String> songsInPlaylist,String newSongid) {
        for(String existingSongid : songsInPlaylist){
            if(existingSongid.equals(newSongid)){
                return true;
            }
        }
        return false;
    }
}
