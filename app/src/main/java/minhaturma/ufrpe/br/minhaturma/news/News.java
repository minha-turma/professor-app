package minhaturma.ufrpe.br.minhaturma.news;

public class News {

    String id;
    String tituloVideo;
    String linkVideo;
    String input;

    public News(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTituloVideo() {
        return tituloVideo;
    }

    public void setTituloVideo(String tituloVideo) {
        this.tituloVideo = tituloVideo;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public String toString() {
        return "News{" +
                "id='" + id + '\'' +
                ", tituloVideo='" + tituloVideo + '\'' +
                ", linkVideo='" + linkVideo + '\'' +
                ", input='" + input + '\'' +
                '}';
    }
}
