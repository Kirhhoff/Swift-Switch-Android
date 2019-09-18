package com.example.win.easy.listener;

import com.example.win.easy.display.interfaces.DisplayService;
import com.example.win.easy.tool.SongListWithSongs;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import java.util.List;

import lombok.Builder;

@Builder
public class OnTabSelectedListenerForSwitchingSongList implements QMUITabSegment.OnTabSelectedListener {

    private DisplayService displayService;
    private List<SongListWithSongs> appearanceLists;

    @Override
    public void onTabSelected(int index) {
        displayService.setDisplayList(appearanceLists.get(index));
    }

    @Override
    public void onTabUnselected(int index) {

    }

    @Override
    public void onTabReselected(int index) {

    }

    @Override
    public void onDoubleTap(int index) {

    }
}
