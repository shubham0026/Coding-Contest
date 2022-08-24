package com.crio.jukebox.exceptions;

public class SongNotFoundInActivePlaylistException extends RuntimeException{
    public SongNotFoundInActivePlaylistException()
    {
     super();
    }
    public SongNotFoundInActivePlaylistException(String msg)
    {
     super(msg);
    }
}
