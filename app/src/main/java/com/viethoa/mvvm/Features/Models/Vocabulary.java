package com.viethoa.mvvm.Features.Models;

import java.io.Serializable;

/**
 * Created by VietHoa on 27/04/16.
 */
public class Vocabulary implements Serializable {
    private int id;
    private String word;
    private String vocalization;
    private String define;
    private String url;

    public Vocabulary(int id, String word, String vocalization, String define, String url) {
        this.id = id;
        this.word = word;
        this.vocalization = vocalization;
        this.define = define;
        this.url = url;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getVocalization() {
        return vocalization;
    }

    public void setVocalization(String vocalization) {
        this.vocalization = vocalization;
    }

    public String getDefine() {
        return define;
    }

    public void setDefine(String define) {
        this.define = define;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
