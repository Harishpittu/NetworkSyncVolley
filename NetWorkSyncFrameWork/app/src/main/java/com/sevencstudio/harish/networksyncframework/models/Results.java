package com.sevencstudio.harish.networksyncframework.models;

import io.realm.RealmObject;

/**
 * Created by harish on 11/01/16.
 */
public class Results extends RealmObject {
    private boolean live;
    private String timestamp;
    private String pitcher_team;
    private int id;
    private String homebase_youtube_id;
    private boolean showmonth;
    private String home_team;
    private String visiting_team;
    private String home_team_score;
    private String visiting_team_score;
    private Live_Video live_video;

    public Live_Video getLive_video() {
        return live_video;
    }

    public void setLive_video(Live_Video live_video) {
        this.live_video = live_video;
    }

    public String getHome_team() {
        return home_team;
    }

    public void setHome_team(String home_team) {
        this.home_team = home_team;
    }

    public String getVisiting_team() {
        return visiting_team;
    }

    public void setVisiting_team(String visiting_team) {
        this.visiting_team = visiting_team;
    }

    public String getHome_team_score() {
        return home_team_score;
    }

    public void setHome_team_score(String home_team_score) {
        this.home_team_score = home_team_score;
    }

    public String getVisiting_team_score() {
        return visiting_team_score;
    }

    public void setVisiting_team_score(String visiting_team_score) {
        this.visiting_team_score = visiting_team_score;
    }

    public boolean isShowmonth() {
        return showmonth;
    }

    public void setShowmonth(boolean showmonth) {
        this.showmonth = showmonth;
    }

    public String getProcessed_video_youtube_id() {
        return processed_video_youtube_id;
    }

    public void setProcessed_video_youtube_id(String processed_video_youtube_id) {
        this.processed_video_youtube_id = processed_video_youtube_id;
    }

    private String processed_video_youtube_id;
    private String firstbase_youtube_id;
    private boolean showdate;
    private String DayNumber;
    private String DayName;

    public String getMonthName() {
        return MonthName;
    }

    public void setMonthName(String monthName) {
        MonthName = monthName;
    }

    private String MonthName;

    public boolean isFuturegame() {
        return futuregame;
    }

    public void setFuturegame(boolean futuregame) {
        this.futuregame = futuregame;
    }

    private boolean futuregame;

    public String getDayName() {
        return DayName;
    }

    public void setDayName(String dayName) {
        DayName = dayName;
    }

    public String getDayNumber() {
        return DayNumber;
    }

    public void setDayNumber(String dayNumber) {
        DayNumber = dayNumber;
    }

    public boolean isShowdate() {
        return showdate;
    }

    public void setShowdate(boolean showdate) {
        this.showdate = showdate;
    }

    public String getMetadata_url() {
        return metadata_url;
    }

    public void setMetadata_url(String metadata_url) {
        this.metadata_url = metadata_url;
    }

    private String metadata_url;

    public String getThirdbase_youtube_id() {
        return thirdbase_youtube_id;
    }

    public void setThirdbase_youtube_id(String thirdbase_youtube_id) {
        this.thirdbase_youtube_id = thirdbase_youtube_id;
    }

    public String getFirstbase_youtube_id() {
        return firstbase_youtube_id;
    }

    public void setFirstbase_youtube_id(String firstbase_youtube_id) {
        this.firstbase_youtube_id = firstbase_youtube_id;
    }

    public String getHomebase_youtube_id() {
        return homebase_youtube_id;
    }

    public void setHomebase_youtube_id(String homebase_youtube_id) {
        this.homebase_youtube_id = homebase_youtube_id;
    }

    private String thirdbase_youtube_id;

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public boolean getLive() {
        return this.live;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPitcher_team() {
        return pitcher_team;
    }

    public void setPitcher_team(String pitcher_team) {
        this.pitcher_team = pitcher_team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
