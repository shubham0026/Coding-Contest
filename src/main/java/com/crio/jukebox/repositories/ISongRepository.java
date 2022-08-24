package com.crio.jukebox.repositories;

import com.crio.jukebox.entities.Song;

public interface ISongRepository extends CRUDRepository<Song,String>{

    //public Song save(Song song);
    public Song findSongById(String id);
    
}
