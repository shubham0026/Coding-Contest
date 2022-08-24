package com.crio.jukebox.repositories;

import com.crio.jukebox.entities.Playlist;

public interface IPlaylistRepository extends CRUDRepository<Playlist,String>{

    Playlist save(String userId, Playlist playlist);
    
}
