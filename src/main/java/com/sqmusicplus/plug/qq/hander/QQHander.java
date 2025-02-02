package com.sqmusicplus.plug.qq.hander;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ejlchina.data.Mapper;
import com.ejlchina.okhttps.HTTP;
import com.ejlchina.okhttps.HttpResult;
import com.ejlchina.okhttps.OkHttps;
import com.ejlchina.okhttps.SHttpTask;
import com.sqmusicplus.base.entity.*;
import com.sqmusicplus.plug.base.PlugBrType;
import com.sqmusicplus.plug.base.hander.SearchHanderAbstract;
import com.sqmusicplus.plug.entity.*;
import com.sqmusicplus.plug.kw.entity.SearchMusicResult;
import com.sqmusicplus.plug.qq.config.QQConfig;
import com.sqmusicplus.plug.qq.entity.QQSearchEntity;
import com.sqmusicplus.plug.qq.enums.QQSearchType;
import com.sqmusicplus.plug.utils.QSignHelper;
import com.sqmusicplus.utils.DateUtils;
import com.sqmusicplus.utils.DownloadUtils;
import com.sqmusicplus.utils.MusicUtils;
import com.sqmusicplus.utils.ZLibUtils;
import com.twelvemonkeys.lang.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Classname QQHander
 * @Description TODO
 * @Version 1.0.0
 * @Date 2023/8/25 9:18
 * @Created by Administrator
 */

@Service("qqHander")
public class QQHander extends SearchHanderAbstract {

    @Autowired
    private QQConfig config;
    @Override
    public  QQConfig getConfig() {
        return  config;
    }

    public QQSearchEntity qqSearchEntity = new QQSearchEntity();

    @Override
    public String getPlugName() {
        return "qq";
    }

    public QQSearchEntity getqqSearchEntity() {
        return qqSearchEntity;
    }

    public void setQqSearchEntity(QQSearchEntity qqSearchEntity) {
        this.qqSearchEntity = qqSearchEntity;
    }

    @Override
    public PlugSearchResult<PlugSearchMusicResult> querySongByName(SearchKeyData searchKeyData) {
        String searchUrl = config.getSearchUrl();
        String s = getqqSearchEntity().searchRequestParam(searchKeyData.getSearchkey(), QQSearchType.MUSIC.getValue(), searchKeyData.getPageIndex(), searchKeyData.getPageSize());
        Mapper mapper = DownloadUtils.getHttp().sync(searchUrl).addHeader("Content-Type","json/application;charset=utf-8").addHeader("Referer","https://y.qq.com").addHeader("User-Agent","QQ%E9%9F%B3%E4%B9%90/73222 CFNetwork/1406.0.3 Darwin/22.4.0").bodyType(OkHttps.JSON).setBodyPara(s).post().getBody().toMapper();
        PlugSearchResult<PlugSearchMusicResult> plugSearchResult = getqqSearchEntity().toMusicPlugSearchResult(mapper, config);
        plugSearchResult.setSearchIndex(searchKeyData.getPageIndex());
        plugSearchResult.setSearchSize(searchKeyData.getPageSize());
        plugSearchResult.setSearchTotal(plugSearchResult.getRecords().size());
        plugSearchResult.setSearchKeyWork(searchKeyData.getSearchkey());
        return plugSearchResult;
    }

    @Override
    public PlugSearchResult<PlugSearchArtistResult> queryArtistByName(SearchKeyData searchKeyData) {
        String searchUrl = config.getSearchUrl();
        String s = getqqSearchEntity().searchRequestParam(searchKeyData.getSearchkey(), QQSearchType.ARTIST.getValue(), searchKeyData.getPageIndex(), searchKeyData.getPageSize());
        Mapper mapper = DownloadUtils.getHttp().sync(searchUrl).addHeader("Content-Type","json/application;charset=utf-8").addHeader("Referer","https://y.qq.com").addHeader("User-Agent","QQ%E9%9F%B3%E4%B9%90/73222 CFNetwork/1406.0.3 Darwin/22.4.0").bodyType(OkHttps.JSON).setBodyPara(s).post().getBody().toMapper();
        PlugSearchResult<PlugSearchArtistResult> artistPlugSearchResult = getqqSearchEntity().toArtistPlugSearchResult(mapper);
        artistPlugSearchResult.setSearchIndex(searchKeyData.getPageIndex());
        artistPlugSearchResult.setSearchSize(searchKeyData.getPageSize());
        artistPlugSearchResult.setSearchTotal(artistPlugSearchResult.getRecords().size());
        artistPlugSearchResult.setSearchKeyWork(searchKeyData.getSearchkey());
        return artistPlugSearchResult;
    }

    @Override
    public PlugSearchResult<PlugSearchAlbumResult> queryAlbumByName(SearchKeyData searchKeyData) {
        String searchUrl = config.getSearchUrl();
        String s = getqqSearchEntity().searchRequestParam(searchKeyData.getSearchkey(), QQSearchType.ALBUM.getValue(), searchKeyData.getPageIndex(), searchKeyData.getPageSize());
        Mapper mapper = DownloadUtils.getHttp().sync(searchUrl).addHeader("Content-Type","json/application;charset=utf-8").addHeader("Referer","https://y.qq.com").addHeader("User-Agent","QQ%E9%9F%B3%E4%B9%90/73222 CFNetwork/1406.0.3 Darwin/22.4.0").bodyType(OkHttps.JSON).setBodyPara(s).post().getBody().toMapper();
        PlugSearchResult<PlugSearchAlbumResult> albumPlugSearchResult = getqqSearchEntity().toAlbumPlugSearchResult(mapper);
        albumPlugSearchResult.setSearchIndex(searchKeyData.getPageIndex());
        albumPlugSearchResult.setSearchSize(searchKeyData.getPageSize());
        albumPlugSearchResult.setSearchTotal(albumPlugSearchResult.getRecords().size());
        albumPlugSearchResult.setSearchKeyWork(searchKeyData.getSearchkey());
        return albumPlugSearchResult;
    }

    @Override
    public Music querySongById(String SongId) {
        String searchUrl = config.getSearchUrl();
        String s = getqqSearchEntity().musicInfoRequestParam(SongId);
        Mapper mapper = DownloadUtils.getHttp().sync(searchUrl).setBodyPara(s).post().getBody().toMapper();
        return getqqSearchEntity().songInfoToMusic(mapper, config);
    }

    @Override
    public Artists queryArtistById(String artistId) {
        Artists plugArtistResult = getqqSearchEntity().toPlugArtistResult(artistId, config);
        return plugArtistResult;

    }

    @Override
    public Album queryAlbumById(String albumId) {
        String searchUrl = config.getSearchUrl();
        String s = getqqSearchEntity().albumInfoRequestParam(albumId);
        Mapper mapper = DownloadUtils.getHttp().sync(searchUrl).setBodyPara(s).post().getBody().toMapper();
        Album album = getqqSearchEntity().albumInfoToAlbum(mapper, config);
        return album;


    }

    @Override
    public String queryLyric(String SongId) {
        String s = getqqSearchEntity().toPlugLyricResult(SongId,config);
        return s;
    }

    @Override
    public List<Album> getAlbumsByArtist(String artistId, Integer pageIndex, Integer pageSize) {
        String searchUrl = config.getSearchUrl();
        String s = getqqSearchEntity().artistsTransferAlbumParam(artistId);
        Mapper mapper = DownloadUtils.getHttp().sync(searchUrl).setBodyPara(s).post().getBody().toMapper();
        List<Album> albums = getqqSearchEntity().artistsTransferAlbum(mapper, config);
        return albums;
    }

    @Override
    public List<Music> getAlbumSongByAlbumsId(String albumsId) {
        String searchUrl = config.getSearchUrl();
        String s = getqqSearchEntity().albumInfoRequestParam(albumsId);
        Mapper mapper = DownloadUtils.getHttp().sync(searchUrl).setBodyPara(s).post().getBody().toMapper();
        List<Music> albumMusic = getqqSearchEntity().albumInfoToAlbumMusic(mapper, config);
        return albumMusic;


    }

    @Override
    public HashMap<String, String> getDownloadUrl(String musicId, PlugBrType brType) {

//        HashMap<String, String> stringStringHashMap = new HashMap<>();
//        stringStringHashMap.put("url", "");
//        stringStringHashMap.put("type", brType.getType());
//        stringStringHashMap.put("bit", brType.getBit().toString());
//        // 获取 mediaMid
//        String infoReqBody = String.format("{\"comm\":{\"ct\":\"19\",\"cv\":\"1859\",\"uin\":\"0\"},\"req\":{\"module\":\"music.pf_song_detail_svr\"," +
//                "\"method\":\"get_song_detail_yqq\",\"param\":{\"song_type\":0,\"song_mid\":\"%s\"}}}", musicId);
//        HTTP http = DownloadUtils.getHttp();
//        String sign = QSignHelper.sign(infoReqBody);
//        HttpResult post1 = http.sync("https://u.y.qq.com/cgi-bin/musics.fcg?format=json&sign=" + sign).setBodyPara(infoReqBody).post();
//        JSONObject infoBodyJson = JSONObject.parseObject(post1.getBody().toString());
//
//        if (infoBodyJson.getIntValue("code") != 0 || infoBodyJson.getJSONObject("req").getIntValue("code") != 0){
//
//            return stringStringHashMap;
//        }
//        String mediaMid = infoBodyJson.getJSONObject("req").getJSONObject("data")
//                .getJSONObject("track_info").getJSONObject("file").getString("media_mid");
//        // 获取 url
//        String reqBody = String.format("{\"req_0\":{\"module\":\"vkey.GetVkeyServer\",\"method\":\"CgiGetVkey\",\"param\":{\"filename\":[\"%s\"]," +
//                        "\"guid\":\"%s\",\"songmid\":[\"%s\"],\"songtype\":[0],\"uin\":\"%s\",\"loginflag\":1,\"platform\":\"20\"}}," +
//                        "\"comm\":{\"qq\":\"%s\",\"authst\":\"%s\",\"ct\":\"26\",\"cv\":\"2010101\",\"v\":\"2010101\"}}",
//                brType.getValue().split("_")[1] + mediaMid +brType.getType(), "0", musicId, "0", "", "");
//        String sign2 = QSignHelper.sign(reqBody);
//        HttpResult post2 = http.sync( "https://u.y.qq.com/cgi-bin/musics.fcg?format=json&sign=" + sign2).setBodyPara(reqBody).post();
//
//
//
//        JSONObject urlJson = JSONObject.parseObject(post2.getBody().toString());
//        JSONObject data = urlJson.getJSONObject("req_0").getJSONObject("data");
//        if (urlJson==null||urlJson.isEmpty()){
//
//            return stringStringHashMap;
//        }
//        String sip = data.getJSONArray("sip").getString(0);
//        String url = data.getJSONArray("midurlinfo").getJSONObject(0).getString("purl");
//        String trackUrl = sip + url;
//        stringStringHashMap.put("url", trackUrl);
//
//        return stringStringHashMap;












        String platform = "qq";
        String t2 = brType.getValue().split("_")[0];
        String device = "MI 14 Pro Max";
        String osVersion = "13" ;
         String time = DateUtils.getNowDate().getTime()/1000+"";
        String  lowerCase = DigestUtil.md5Hex("6d849adb2f3e00d413fe48efbb18d9bb" + time + "6562653262383463363633646364306534333668");
        String   s6 = "{\\\"method\\\":\\\"GetMusicUrl\\\",\\\"platform\\\":\\\"" + platform + "\\\",\\\"t1\\\":\\\"" + musicId + "\\\",\\\"t2\\\":\\\"" + t2 + "\\\"}";
        String s7 = "{\\\"uid\\\":\\\"\\\",\\\"token\\\":\\\"\\\",\\\"deviceid\\\":\\\"84ac82836212e869dbeea73f09ebe52b\\\",\\\"appVersion\\\":\\\"4.1.2\\\",\\\"vercode\\\":\\\"4120\\\",\\\"device\\\":\\\"" + device + "\\\",\\\"osVersion\\\":\\\"" + osVersion + "\\\"}";
        String  s8 = "{\n\t\"text_1\":\t\"" + s6 + "\",\n\t\"text_2\":\t\"" + s7 + "\",\n\t\"sign_1\":\t\"" + lowerCase + "\",\n\t\"time\":\t\"" + time + "\",\n\t\"sign_2\":\t\"" + DigestUtil.md5Hex(
                s6.replace("\\", "") + s7.replace("\\", "") + lowerCase + time + "NDRjZGIzNzliNzEe") + "\"\n}" ;
        byte[] utf8Bytes = s8.getBytes(StandardCharsets.UTF_8);
        String hexString = ByteArrayUtil.toHexString(utf8Bytes);
        String upperHexString = hexString.toUpperCase();
        byte[] encodedBytes = upperHexString.getBytes(StandardCharsets.UTF_8);
        byte[] compress = ZLibUtils.compress(encodedBytes);
        HTTP http = DownloadUtils.getHttp();
//        SHttpTask sync = http.sync(config.getDownloadUrl());
        SHttpTask sync = http.sync("http://gcsp.kzti.top:1030/client/cgi-bin/api.fcg");
        sync.setBodyPara(compress);
        HttpResult post = sync.post();
        byte[] decompress = ZLibUtils.decompress(post.getBody().toBytes());
        String s = new String(decompress);
        JSONObject jsonObject = JSONObject.parseObject(s);
        String downloadurl = jsonObject.getString("data");
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("url", downloadurl);
        stringStringHashMap.put("type", brType.getType());
        stringStringHashMap.put("bit", brType.getBit().toString());
        return stringStringHashMap;
//        String deonloadType = "flac";
//        Integer bit = brType.getBit();
//        if (bit == 128) {
//            deonloadType = "128k";
//        } else if (bit == 320) {
//            deonloadType = "320k";
//        } else {
//            deonloadType =  "flac";
//        }
//        HTTP http = DownloadUtils.getHttp();
//        QQConfig config = getConfig();
//        String downloadUrl = config.getDownloadUrl();
//        downloadUrl = downloadUrl.replaceAll("#\\{pmid}", musicId).replaceAll("#\\{brType}", deonloadType);
//        HttpResult post = http.sync(downloadUrl).addHeader("X-Request-Key","ikunsource")
//                .addHeader("Accept","*/*")
//                .addHeader("Accept-Encoding","gzip, deflate, br")
//                .get();
//        Mapper mapper = post.getBody().toMapper();
//        int code = mapper.getInt("code");
//        if (code != 0) {
//            return null;
//        }
//        String downloadurl = mapper.getString("data");
//        HashMap<String, String> stringStringHashMap = new HashMap<>();
//        stringStringHashMap.put("url", downloadurl);
//        stringStringHashMap.put("type", brType.getType());
//        stringStringHashMap.put("bit", brType.getBit().toString());
//        return stringStringHashMap;
    }

    @Override
    public DownloadEntity downloadSong(String musicid, PlugBrType brType, String musicname, String artistname, String albumname, Boolean isAudioBook, String addSubsonicPlayListName) {
        Music music = querySongById(musicid);
        DownloadEntity downloadEntity = new DownloadEntity("qqHander",musicid, brType, music.getMusicName(), music.getMusicArtists(), music.getMusicAlbum(), isAudioBook, isAudioBook?addSubsonicPlayListName:null);
        return downloadEntity;
    }

    @Override
    public DownloadEntity downloadSong(Music music, PlugBrType brType, Boolean isAudioBook, String addSubsonicPlayListName) {
        DownloadEntity downloadEntity = new DownloadEntity("qqHander",music.getId(), brType, music.getMusicName(), music.getMusicArtists(), music.getMusicAlbum(), isAudioBook, isAudioBook?addSubsonicPlayListName:null);
        return downloadEntity;
    }

    @Override
    public DownloadEntity downloadSong(Music music, PlugBrType brType, String addSubsonicPlayListName) {
        DownloadEntity downloadEntity = new DownloadEntity("qqHander",music.getId(), brType, music.getMusicName(), music.getMusicArtists(), music.getMusicAlbum(), false, addSubsonicPlayListName);
        return downloadEntity;
    }

    @Override
    public ArrayList<DownloadEntity> downloadAlbum(String albumsId, PlugBrType brType, String addSubsonicPlayListName, String artist, Boolean isAudioBook, String albumName) {
        List<Music> musiclist = getAlbumSongByAlbumsId(albumsId);
        AtomicReference<String> change = new AtomicReference<>(artist);
        ArrayList<DownloadEntity> downloadEntities = new ArrayList<>();

        SqConfig accompaniment = getConfigService().getOne(new QueryWrapper<SqConfig>().eq("config_key", "music.ignore.accompaniment"));
        SqConfig matchAlbumSinger = getConfigService().getOne(new QueryWrapper<SqConfig>().eq("config_key", "music.strong.match.album.singer"));
        SqConfig albumSingerUnity = getConfigService().getOne(new QueryWrapper<SqConfig>().eq("config_key", "music.album.singer.unity"));

        musiclist.forEach(md -> {
            if (Boolean.getBoolean(accompaniment.getConfigValue())) {
                if (md.getMusicName().contains("(伴奏)") || md.getMusicName().contains("(试听版)") || md.getMusicName().contains("片段")) {
                    return;
                }
            }
            if (Boolean.getBoolean(matchAlbumSinger.getConfigValue()) && !isAudioBook) {
                if (!md.getMusicArtists().contains(change.get())) {
                    return;
                }
            }
            if (!Boolean.getBoolean(albumSingerUnity.getConfigValue()) && !isAudioBook) {
                change.set(md.getMusicArtists());
            }
            if (isAudioBook) {
                downloadEntities.add(new DownloadEntity("qqHander",md.getId(), brType, md.getMusicName(), artist, albumName, isAudioBook));
            } else {
                //添加到缓存
                downloadEntities.add(new DownloadEntity("qqHander",md.getId(), brType, md.getMusicName(), change.get(), md.getMusicAlbum()));
            }

        });
        return downloadEntities;

    }

    @Override
    public List<DownloadEntity> downloadArtistAllSong(String artistId, PlugBrType brType, String addSubsonicPlayListName) {
        String searchUrl = config.getSearchUrl();
        String s = getqqSearchEntity().artistsTransferAlbumParam(artistId);
        Mapper mapper = DownloadUtils.getHttp().sync(searchUrl).setBodyPara(s).post().getBody().toMapper();
        List<Album> albums = getqqSearchEntity().artistsTransferAlbum(mapper, config);
        ArrayList<DownloadEntity> downloadEntitys = new ArrayList<>();
        for (Album album : albums) {
            ArrayList<DownloadEntity> downloadEntities = downloadAlbum(album.getAlbumId(), brType, addSubsonicPlayListName, album.getAlbumArtists(), false, album.getAlbumName());
            downloadEntitys.addAll(downloadEntities);
        }
        return downloadEntitys;
    }

    @Override
    public List<DownloadEntity> downloadArtistAllAlbum(String artistId, PlugBrType brType, String addSubsonicPlayListName) {
        String searchUrl = config.getSearchUrl();
        String s = getqqSearchEntity().artistsTransferAlbumParam(artistId);
        Mapper mapper = DownloadUtils.getHttp().sync(searchUrl).setBodyPara(s).post().getBody().toMapper();
        List<Album> albums = getqqSearchEntity().artistsTransferAlbum(mapper, config);
        ArrayList<DownloadEntity> downloadEntitys = new ArrayList<>();
        for (Album album : albums) {
            ArrayList<DownloadEntity> downloadEntities = downloadAlbum(album.getAlbumId(), brType, addSubsonicPlayListName, album.getAlbumArtists(), false, album.getAlbumName());
            downloadEntitys.addAll(downloadEntities);
        }
        return downloadEntitys;
    }


}
