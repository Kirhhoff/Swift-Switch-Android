package com.example.win.easy;

import android.content.Context;

import com.example.win.easy.display.DisplayServiceAdapter;
import com.example.win.easy.tool.SongListWithSongs;
import com.example.win.easy.value_object.SongListVO;
import com.example.win.easy.value_object.SongVO;
import com.example.win.easy.view.OnClickFunc;
import com.example.win.easy.viewmodel.SongViewModel;
import com.qmuiteam.qmui.layout.QMUILinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dashboard extends QMUILinearLayout  {

    private SongViewModel songViewModel;
    private DisplayServiceAdapter displayServiceAdapter;
    private SwitchTab switchTab;
    private String defaultSongListName="所有歌曲";

    public Dashboard(Context context) {
        super(context);
    }

    public void search(List<Character> sequence){
        //TODO 从viewModel获取
        List<SongVO> songsMatched=new ArrayList<>();

        List<SongListWithSongs> filledSongListGroupedBySource=groupBySource(songsMatched);
        OnClickFunc<SongVO> songOnClickFunc=songOnClickFuncWhenSearching();
        OnClickFunc<SongListVO> tabOnClickFunc=tabOnClickFuncWhenSearching();

        switchTab.setPagesWithTab(filledSongListGroupedBySource,tabOnClickFunc,songOnClickFunc);
    }

    private OnClickFunc<SongVO> songOnClickFuncWhenSearching(){
        return (song,itemView)->{
            //TODO
            List<SongListWithSongs> filledSongListsContainIt= filledSongListsContain(song);
            displayServiceAdapter.startWith(song,filledSongListsContainIt.get(0));
        };
    }

    private List<SongListWithSongs> filledSongListsContain(SongVO songVO){
        Map<SongListVO,List<SongVO>> songListToSongsMap=new HashMap<>();

        //TODO 从viewModel获取
        List<SongListVO> songListsContainIt=new ArrayList<>();

        return isEmpty(songListsContainIt)
                ? defaultFilledSongList()
                : fillMapWithSongs();
    }

    private boolean isEmpty(List<SongListVO> songLists){
        return songLists.size()==0;
    }

    private List<SongListWithSongs> defaultFilledSongList(){
        List<SongListWithSongs> singleSongListList=new ArrayList<>();
        //TODO 从viewModel获取
        List<SongVO> allSongs=new ArrayList<>();
        SongListVO defaultSongList=createDefaultSongList();
//        songListToSongsMap.put(defaultSongList,allSongs);
    }

    private SongListVO createDefaultSongList(){
        return SongListVO.builder()
                .name(defaultSongListName)
                .build();
    }

    private void fillMapWithSongs(List<SongListVO> songListsContainIt, Map<SongListVO, List<SongVO>> songListToSongsMap){
        //TODO
        for (SongListVO songList:songListsContainIt)
            SongListWithSongs
    }

    private OnClickFunc<SongListVO> tabOnClickFuncWhenSearching(){
        return (songList,itemView)->{};
    }

    private OnClickFunc<SongVO> songOnClickFuncWhenSwitching(){
        return (song,itemView)->{
            //TODO
        };
    }

    private List<SongListWithSongs> groupBySource(List<SongVO> songs){
        //TODO
        return null;
    }
}
