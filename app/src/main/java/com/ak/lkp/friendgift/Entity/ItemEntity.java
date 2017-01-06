package com.ak.lkp.friendgift.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by likunpeng on 2017/1/6.
 */

public class ItemEntity implements Serializable{

    /**
     * code : 200
     * data : {"candidates":[{"editable":true,"id":10,"name":"送女票","url":""},{"editable":true,"id":129,"name":"海淘","url":""},{"editable":true,"id":28,"name":"科技范","url":""},{"editable":true,"id":118,"name":"美食","url":""},{"editable":true,"id":26,"name":"送基友","url":""},{"editable":true,"id":6,"name":"送爸妈","url":""},{"editable":true,"id":17,"name":"送同事","url":""},{"editable":true,"id":24,"name":"送宝贝","url":""},{"editable":true,"id":127,"name":"设计感","url":""},{"editable":true,"id":125,"name":"创意生活","url":""},{"editable":true,"id":14,"name":"文艺风","url":""},{"editable":true,"id":126,"name":"奇葩搞怪","url":""},{"editable":true,"id":121,"name":"数码","url":""},{"editable":true,"id":11,"name":"萌萌哒","url":""}],"channels":[{"editable":false,"id":106,"name":"精选"},{"editable":true,"id":10,"name":"送女票","url":""},{"editable":true,"id":129,"name":"海淘","url":""},{"editable":true,"id":28,"name":"科技范","url":""},{"editable":true,"id":118,"name":"美食","url":""},{"editable":true,"id":26,"name":"送基友","url":""},{"editable":true,"id":6,"name":"送爸妈","url":""},{"editable":true,"id":17,"name":"送同事","url":""},{"editable":true,"id":24,"name":"送宝贝","url":""},{"editable":true,"id":127,"name":"设计感","url":""},{"editable":true,"id":125,"name":"创意生活","url":""},{"editable":true,"id":14,"name":"文艺风","url":""},{"editable":true,"id":126,"name":"奇葩搞怪","url":""},{"editable":true,"id":121,"name":"数码","url":""},{"editable":true,"id":11,"name":"萌萌哒","url":""}]}
     * message : OK
     */


    private DataBean data;



    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }



    public static class DataBean {
        private List<CandidatesBean> candidates;
        private List<ChannelsBean> channels;

        public List<CandidatesBean> getCandidates() {
            return candidates;
        }

        public void setCandidates(List<CandidatesBean> candidates) {
            this.candidates = candidates;
        }

        public List<ChannelsBean> getChannels() {
            return channels;
        }

        public void setChannels(List<ChannelsBean> channels) {
            this.channels = channels;
        }

        public static class CandidatesBean {
            /**
             * editable : true
             * id : 10
             * name : 送女票
             * url :
             */

            private boolean editable;
            private int id;
            private String name;
            private String url;

            public boolean isEditable() {
                return editable;
            }

            public void setEditable(boolean editable) {
                this.editable = editable;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class ChannelsBean {
            /**
             * editable : false
             * id : 106
             * name : 精选
             * url :
             */

            private boolean editable;
            private int id;
            private String name;
            private String url;

            public boolean isEditable() {
                return editable;
            }

            public void setEditable(boolean editable) {
                this.editable = editable;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
