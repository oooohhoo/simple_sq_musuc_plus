if(!self.define){let s,e={};const l=(l,i)=>(l=new URL(l+".js",i).href,e[l]||new Promise((e=>{if("document"in self){const s=document.createElement("script");s.src=l,s.onload=e,document.head.appendChild(s)}else s=l,importScripts(l),e()})).then((()=>{let s=e[l];if(!s)throw new Error(`Module ${l} didn’t register its module`);return s})));self.define=(i,r)=>{const n=s||("document"in self?document.currentScript.src:"")||location.href;if(e[n])return;let a={};const o=s=>l(s,n),t={module:{uri:n},exports:a,require:o};e[n]=Promise.all(i.map((s=>t[s]||o(s)))).then((s=>(r(...s),a)))}}define(["./workbox-fa446783"],(function(s){"use strict";self.skipWaiting(),s.clientsClaim(),s.precacheAndRoute([{url:"assets/Add-3c4a749c.js",revision:null},{url:"assets/api-efb7f5c8.js",revision:null},{url:"assets/context-96c33be8.js",revision:null},{url:"assets/Download-08d401c1.css",revision:null},{url:"assets/Download-9b61b6cb.js",revision:null},{url:"assets/Form-24a2e081.js",revision:null},{url:"assets/Home-b1090911.js",revision:null},{url:"assets/index-c5038044.css",revision:null},{url:"assets/index-c5841820.js",revision:null},{url:"assets/Input-8455744a.js",revision:null},{url:"assets/Login-442bfe1d.js",revision:null},{url:"assets/Login-cc684101.css",revision:null},{url:"assets/ParserPlaylist-638a4b28.js",revision:null},{url:"assets/ParserPlaylist-c7497bf3.css",revision:null},{url:"assets/Parsertext-292c529b.js",revision:null},{url:"assets/Parsertext-2c56521c.css",revision:null},{url:"assets/Search-a2afe438.css",revision:null},{url:"assets/Search-f96117fa.js",revision:null},{url:"assets/Set-ea09dcdd.js",revision:null},{url:"assets/Space-522631d1.js",revision:null},{url:"assets/Switch-5810f9c6.js",revision:null},{url:"assets/Thing-2ed82334.js",revision:null},{url:"assets/Tooltip-e9be815c.js",revision:null},{url:"assets/TopWitge-92bafbde.js",revision:null},{url:"index.html",revision:"a2ae6b8483c555fca51bd9b716b233ce"},{url:"pwa/logo.png",revision:"a4002c8e6971fa121256144d5ad2bf98"},{url:"registerSW.js",revision:"1872c500de691dce40960bb85481de07"},{url:"vite.svg",revision:"8e3a10e157f75ada21ab742c022d5430"},{url:"./pwa/logo.png",revision:"a4002c8e6971fa121256144d5ad2bf98"},{url:"manifest.webmanifest",revision:"caf34c20a8566ecc834db127daac74ae"}],{}),s.cleanupOutdatedCaches(),s.registerRoute(new s.NavigationRoute(s.createHandlerBoundToURL("index.html")))}));