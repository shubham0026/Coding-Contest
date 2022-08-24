package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.Playlist;

public class PlaylistRepository implements IPlaylistRepository {
    private final Map<String,Playlist> playlistMap;
    private Integer autoIncrement = 0;
    
    public PlaylistRepository() {
        playlistMap = new HashMap<String,Playlist>();
    }

    public PlaylistRepository(Map<String, Playlist> playlistMap) {
        this.playlistMap = playlistMap;
        this.autoIncrement = playlistMap.size();
    }

    @Override
    public Playlist save(Playlist entity) {
        // if( entity.getId() == null ){
        //     autoIncrement++;
        //     Song s = new Song(Integer.toString(autoIncrement),entity.getSongName(),entity.getGenre(),entity.getAlbumName(),entity.getAlbumArtist(),entity.getFeaturedArtist());
        //     songMap.put(s.getId(),s);
        //     return s;
        // }
        
       
        playlistMap.put(entity.getId(),entity);
        return entity;
    }

    @Override
    public List<Playlist> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Playlist> findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void delete(Playlist entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Playlist save(String userId, Playlist playlist) {
        // TODO Auto-generated method stub
        return null;
    }
}
