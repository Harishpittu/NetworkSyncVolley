package com.sevencstudio.harish.networksyncframework.models;

import io.realm.RealmObject;

/**
 * Created by harish on 25/02/16.
 */
public class Live_Video extends RealmObject{

    private String server_url;
    private String stream_key;
    private String live_stream_video_id;

    public String getServer_url() {
        return server_url;
    }

    public void setServer_url(String server_url) {
        this.server_url = server_url;
    }

    public String getStream_key() {
        return stream_key;
    }

    public void setStream_key(String stream_key) {
        this.stream_key = stream_key;
    }

    public String getLive_stream_video_id() {
        return live_stream_video_id;
    }

    public void setLive_stream_video_id(String live_stream_video_id) {
        this.live_stream_video_id = live_stream_video_id;
    }
}
