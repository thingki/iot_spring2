<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style class="cp-pen-styles">

*, *::before, *::after {
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}

.menu-icon {
  position: relative;
  display: block;
  width: 2.5em;
  height: 2.5em;
  cursor: pointer;
}
.menu-icon:hover span {
  background: #222;
}
.menu-icon:hover span:before, .menu-icon:hover span:after {
  background: #222;
}

.menu-icon > span {
  position: absolute;
  top: 50%;
  display: block;
  width: 100%;
  height: 0.25em;
  background-color: #bbbbbb;
  border-radius: 3px;
  -webkit-transition: -webkit-transform 0.3s, background 0.25s ease;
  -webkit-transition: background 0.25s ease, -webkit-transform 0.3s;
  transition: background 0.25s ease, -webkit-transform 0.3s;
  transition: transform 0.3s, background 0.25s ease;
  transition: transform 0.3s, background 0.25s ease, -webkit-transform 0.3s;
}

.menu-icon > span:before,
.menu-icon > span:after {
  content: "";
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: #bbbbbb;
  border-radius: 3px;
  -webkit-transition: -webkit-transform 0.3s, background 0.25s ease;
  -webkit-transition: background 0.25s ease, -webkit-transform 0.3s;
  transition: background 0.25s ease, -webkit-transform 0.3s;
  transition: transform 0.3s, background 0.25s ease;
  transition: transform 0.3s, background 0.25s ease, -webkit-transform 0.3s;
}

.menu-icon > span:before {
  -webkit-transform: translateY(-0.75em);
  transform: translateY(-0.75em);
}

.menu-icon > span:after {
  -webkit-transform: translateY(0.75em);
  transform: translateY(0.75em);
}

/* OPENED */
.menu-icon.open {
  -webkit-transform: rotate(45deg);
  transform: rotate(45deg);
}

.menu-icon.open > span:before {
  -webkit-transform: rotate(90deg);
  transform: rotate(90deg);
}

.menu-icon.open > span:after {
  -webkit-transform: rotate(90deg);
  transform: rotate(90deg);
}

nav {
  position: fixed;
  top: 0;
  left: 5%;
  color: white;
  z-index: 1;
}
nav ul li.animate {
  top: 0;
}
nav ul li {
  display: inline-block;
  text-align: center;
  margin: 1em 2em;
  cursor: pointer;
  position: relative;
  top: -8em;
}
nav ul li:nth-child(1) {
  -moz-transition: top 0.3s ease 0.15s;
  -o-transition: top 0.3s ease 0.15s;
  -webkit-transition: top 0.3s ease;
  -webkit-transition-delay: 0.15s;
  -webkit-transition: top 0.3s ease 0.15s;
  transition: top 0.3s ease 0.15s;
}
nav ul li:nth-child(2) {
  -moz-transition: top 0.3s ease 0.2s;
  -o-transition: top 0.3s ease 0.2s;
  -webkit-transition: top 0.3s ease;
  -webkit-transition-delay: 0.2s;
  -webkit-transition: top 0.3s ease 0.2s;
  transition: top 0.3s ease 0.2s;
}
nav ul li:nth-child(3) {
  -moz-transition: top 0.3s ease 0.25s;
  -o-transition: top 0.3s ease 0.25s;
  -webkit-transition: top 0.3s ease;
  -webkit-transition-delay: 0.25s;
  -webkit-transition: top 0.3s ease 0.25s;
  transition: top 0.3s ease 0.25s;
}
nav ul li:nth-child(4) {
  -moz-transition: top 0.3s ease 0.3s;
  -o-transition: top 0.3s ease 0.3s;
  -webkit-transition: top 0.3s ease;
  -webkit-transition-delay: 0.3s;
  -webkit-transition: top 0.3s ease 0.3s;
  transition: top 0.3s ease 0.3s;
}
nav ul li:nth-child(5) {
  -moz-transition: top 0.3s ease 0.35s;
  -o-transition: top 0.3s ease 0.35s;
  -webkit-transition: top 0.3s ease;
  -webkit-transition-delay: 0.35s;
  -webkit-transition: top 0.3s ease 0.35s;
  transition: top 0.3s ease 0.35s;
}
nav ul li:hover {
  opacity: 1;
}
nav ul li i {
  font-size: 1.5em;
  background: #ffe21e;
  padding: 0.75em;
  border-radius: 50%;
  color: white;
  display: inline-block;
  overflow: hidden;
}
nav ul li h4 {
  line-height: 2.5;
}
</style>
</head>
<body>
<nav>
  <ul>
    <li>
      <i class="icon-tools"></i>
      <h4>TOOLS</h4>
    </li>
    <li>
      <i class="icon-cog"></i>
      <h4>SETTINGS</h4>
    </li>
    <li>
      <i class="icon-lifebuoy"></i>
      <h4>LEARN</h4>
    </li>
    <li>
      <i class="icon-air"></i>
      <h4>WAVES</h4>
    </li>
    <li>
      <i class="icon-suitcase"></i>
      <h4>TRAVEL</h4>
    </li>
  </ul>
</nav>
<div class="container"><div class="menu-icon"><span></span></div></div>
<script >
(function($){
  $(".menu-icon").on("click", function(){
    	$(this).toggleClass("open");
    	$(".container").toggleClass("nav-open");
    	$("nav ul li").toggleClass("animate");
  });
})(jQuery);
//# sourceURL=pen.js
</script>
</body></html>