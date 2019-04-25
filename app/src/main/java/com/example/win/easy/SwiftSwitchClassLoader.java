package com.example.win.easy;

import com.example.win.easy.view.DashboardView;
import com.example.win.easy.view.MediaPlayerView;
import com.example.win.easy.view.SongListPanelView;
import com.example.win.easy.view.SongPanelView;

public class SwiftSwitchClassLoader {

    private static SwiftSwitchClassLoader instance=new SwiftSwitchClassLoader();
    public static SwiftSwitchClassLoader getInstance(){return instance;}
    private SwiftSwitchClassLoader(){}

    public static void init(){
        DashboardView.getInstance();
        MediaPlayerView.getInstance();
        SongListPanelView.getInstance();
        SongPanelView.getInstance();
    }
}
