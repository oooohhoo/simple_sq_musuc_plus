import{bR as o}from"./index-2d7ac089.js";const r="";function a(t,n){return o({url:r+"/login",method:"post",data:{username:t,password:n}})}function d(){return o({url:r+"/logout",method:"post"})}function i(){return o({url:r+"/isLogin",method:"post"})}function c(){return o({url:r+"/set/getSetList/",method:"get"})}function f(t,n){return o({url:r+"/set/modify",method:"post",data:{configKey:t,configValue:n}})}function m(){return o({url:r+"/set/selectOption",method:"get"})}function h(t,n="music",s,e=20,u=1){if(console.log(t,n,s,e,u),n==="music")return o({url:r+"/searchMusic/"+t+"/"+s+"/"+e+"/"+u,method:"get"});if(n==="album")return o({url:r+"/searchAlbum/"+t+"/"+s+"/"+e+"/"+u,method:"get"});if(n==="artist")return o({url:r+"/searchArtist/"+t+"/"+s+"/"+e+"/"+u,method:"get"});if(n==="artistAllSong")return o({url:r+"/searchArtist/"+t+"/"+s+"/"+e+"/"+u,method:"get"})}function g(t="0",n="kw",s=2e3){return o({url:r+"/musicDownload",method:"post",data:{id:t,plugType:n,br:s}})}function w(t="0",n="kw",s=2e3){return o({url:r+"/ArtistDownload",method:"post",data:{id:t,plugType:n,br:s}})}function b(t="0",n="kw",s=2e3){return o({url:r+"/AlbumDownload",method:"post",data:{id:t,plugType:n,br:s}})}function A(t,n=20,s=1){return o({url:r+"/downloadInfo/getDownloadInfo/"+t+"/"+n+"/"+s,method:"get"})}function k(){return o({url:r+"/downloadInfo/delErrorTask",method:"get"})}function D(){return o({url:r+"/downloadInfo/delSuccessTask",method:"get"})}function p(){return o({url:r+"/downloadInfo/delWaitingTask",method:"get"})}function T(){return o({url:r+"/downloadInfo/refreshTask",method:"get"})}function I(t){return o({url:r+"/downloadParser",method:"post",data:{text:t,br:"2000",plugType:"kw"}})}function L(t,n,s,e){return o({url:r+"/parserUrlAndDownload",method:"post",data:{url:t,isAudioBook:n,bookName:s,artist:e,br:"2000",plugType:"kw"}})}export{w as A,h as a,b,A as c,k as d,D as e,p as f,c as g,f as h,i,I as j,d as k,a as l,g as m,L as p,T as r,m as s};