package com.neil.easywords;

import java.util.List;

/**
 * Created by 01139937 on 2016/9/28.
 */
public class ExampleBean {

    /**
     * msg : SUCCESS
     * status_code : 0
     * data : [{"id":443808,"user":{"username":"username","nickname":"nickanme","id":72196,
     * "avatar":"www.baidu.com"},"unlikes":0,"likes":3,"translation":"跟大家打个招呼","annotation":"say
     * <vocab>hello<\/vocab> to everybody","version":0},{"id":443808,
     * "user":{"username":"username","nickname":"nickanme","id":72198,"avatar":"www.baidu.com"},
     * "unlikes":0,"likes":3,"translation":"跟大家打个招呼","annotation":"say <vocab>hello<\/vocab> to
     * everybody","version":1}]
     */

    private String msg;
    private int status_code;
    /**
     * id : 443808
     * user : {"username":"username","nickname":"nickanme","id":72196,"avatar":"www.baidu.com"}
     * unlikes : 0
     * likes : 3
     * translation : 跟大家打个招呼
     * annotation : say <vocab>hello</vocab> to everybody
     * version : 0
     */

    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        /**
         * username : username
         * nickname : nickanme
         * id : 72196
         * avatar : www.baidu.com
         */

        private UserBean user;
        private int unlikes;
        private int likes;
        private String translation;
        private String annotation;
        private int version;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public int getUnlikes() {
            return unlikes;
        }

        public void setUnlikes(int unlikes) {
            this.unlikes = unlikes;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public String getTranslation() {
            return translation;
        }

        public void setTranslation(String translation) {
            this.translation = translation;
        }

        public String getAnnotation() {
            return annotation;
        }

        public void setAnnotation(String annotation) {
            this.annotation = annotation;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public static class UserBean {
            private String username;
            private String nickname;
            private int id;
            private String avatar;

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }
    }
}
