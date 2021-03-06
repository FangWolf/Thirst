package com.fangwolf.module_news.bean;

import java.util.List;

/**
 * @Auther 獠牙血狼
 * @Date 2019/4/14
 * @Desc 分类的数据bean
 */
public class DataBean {


    /**
     * error : false
     * results : [{"_id":"5cac390c9d2122031c18f571","createdAt":"2019-04-09T06:17:48.297Z","desc":"开源的IM 服务器","publishedAt":"2019-04-10T13:24:56.847Z","source":"web","type":"瞎推荐","url":"https://github.com/wildfirechat/server","used":true,"who":"潇湘剑雨"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 5cac390c9d2122031c18f571
         * createdAt : 2019-04-09T06:17:48.297Z
         * desc : 开源的IM 服务器
         * publishedAt : 2019-04-10T13:24:56.847Z
         * source : web
         * type : 瞎推荐
         * url : https://github.com/wildfirechat/server
         * used : true
         * who : 潇湘剑雨
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
