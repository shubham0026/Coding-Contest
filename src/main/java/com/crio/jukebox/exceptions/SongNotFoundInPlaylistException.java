package com.crio.jukebox.exceptions;

public class SongNotFoundInPlaylistException extends RuntimeException{
    public SongNotFoundInPlaylistException()
    {
     super();
    }
    public SongNotFoundInPlaylistException(String msg)
    {
     super(msg);
    }
}
