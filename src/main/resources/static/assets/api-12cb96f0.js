import{co as t}from"./index-425cfa13.js";const n="";function m(o,r){return t({url:n+"/login",method:"post",data:{username:o,password:r}})}function h(){return t({url:n+"/logout",method:"post"})}function w(){return t({url:n+"/isLogin",method:"post"})}function g(){return t({url:n+"/set/getSetList/",method:"get"})}function A(o,r){return t({url:n+"/set/modify",method:"post",data:{configKey:o,configValue:r}})}function k(){return t({url:n+"/set/selectOption",method:"get"})}function p(o,r="music",e,s=20,a=1){if(console.log(o,r,e,s,a),r==="music")return t({url:n+"/searchMusic/"+o+"/"+e+"/"+s+"/"+a,method:"get"});if(r==="album")return t({url:n+"/searchAlbum/"+o+"/"+e+"/"+s+"/"+a,method:"get"});if(r==="artist")return t({url:n+"/searchArtist/"+o+"/"+e+"/"+s+"/"+a,method:"get"});if(r==="artistAllSong")return t({url:n+"/searchArtist/"+o+"/"+e+"/"+s+"/"+a,method:"get"})}function D(o="0",r="kw",e=2e3){return t({url:n+"/musicDownload",method:"post",data:{id:o,plugType:r,br:e}})}function b(o="0",r="kw"){return t({url:n+"/musicUrl/"+r+"/"+o,method:"get"})}function I(o="0",r="kw",e=2e3){return t({url:n+"/ArtistDownload",method:"post",data:{id:o,plugType:r,br:e}})}function T(o="0",r="kw",e=2e3){return t({url:n+"/AlbumDownload",method:"post",data:{id:o,plugType:r,br:e}})}function U(o,r=20,e=1){return t({url:n+"/downloadInfo/getDownloadInfo/"+o+"/"+r+"/"+e,method:"get"})}function L(o){return t({url:n+"/downloadInfo/deleteDownloadInfo/",method:"post",data:{id:o}})}function S(o){return t({url:n+"/downloadInfo/refresh/status",method:"post",data:{id:o}})}function v(o,r,e,s,a,l,u,d,i=20,c=1){return console.log("dasdas",u,d),t({url:n+"/downloadInfo/getDownloadInfo/search",method:"post",data:{downloadMusicname:o,downloadArtistname:r,downloadAlbumname:e,downloadType:s,audioBook:a,status:l,downloadTimeStart:u,downloadTimeEnd:d,pageSize:i,pageIndex:c}})}function P(){return t({url:n+"/downloadInfo/delErrorTask",method:"get"})}function B(){return t({url:n+"/downloadInfo/delSuccessTask",method:"get"})}function E(){return t({url:n+"/downloadInfo/delWaitingTask",method:"get"})}function M(){return t({url:n+"/downloadInfo/refreshTask",method:"get"})}function O(o){return t({url:n+"/downloadParser",method:"post",data:{text:o,br:"2000",plugType:"kw"}})}function W(o,r,e,s){return t({url:n+"/parserUrlAndDownload",method:"post",data:{url:o,isAudioBook:r,bookName:e,artist:s,br:"2000",plugType:"kw"}})}function j(){return t({url:n+"/set/version",method:"get"})}export{I as A,p as a,b,T as c,U as d,P as e,B as f,g,E as h,w as i,L as j,S as k,m as l,D as m,A as n,O as o,v as p,W as q,M as r,k as s,j as t,h as u};
