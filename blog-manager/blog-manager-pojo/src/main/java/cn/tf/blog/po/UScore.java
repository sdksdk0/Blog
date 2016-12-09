package cn.tf.blog.po;

public class UScore {
    private String scoreid;

    private Integer score;

    private String username;

    private Long money;

    public String getScoreid() {
        return scoreid;
    }

    public void setScoreid(String scoreid) {
        this.scoreid = scoreid == null ? null : scoreid.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long price) {
        this.money = price;
    }
}