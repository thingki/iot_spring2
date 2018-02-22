<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<script src='//static.codepen.io/assets/editor/live/console_runner-ce3034e6bde3912cc25f83cccb7caa2b0f976196f2f2d52303a462c826d54a73.js'></script>
<script src='//static.codepen.io/assets/editor/live/css_live_reload_init-890dc39bb89183d4642d58b1ae5376a0193342f9aed88ea04330dc14c8d52f55.js'></script>
<meta name="robots" content="noindex">
<link rel="shortcut icon" type="image/x-icon" href="//static.codepen.io/assets/favicon/favicon-8ea04875e70c4b0bb41da869e81236e54394d63638a1ef12fa558a4a835f1164.ico" />
<link rel="mask-icon" type="" href="//static.codepen.io/assets/favicon/logo-pin-f2d2b6d2c61838f7e76325261b7195c27224080bc099486ddd6dccb469b8e8e6.svg" color="#111" />
<link rel="canonical" href="https://codepen.io/graphilla/pen/zEZKpN?limit=all&page=2&q=time" />
<link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css'>
<link rel='stylesheet prefetch' href='https://codepen.io/graphilla/pen/1d0635c13cc2f08cddd7afd9c1222886.css?editors=0100'>

</head>
<body>

<div class="series-menu"></div>
<div class="clock">
  <div class="block" data-num="0"></div>
  <div class="block" data-num="1"></div>
  <div class="block" data-num="2"></div>
  <div class="block" data-num="3"></div>
  <div class="block" data-num="4"></div>
  <div class="block" data-num="5"></div>
  <div class="block" data-num="6"></div>
  <div class="block" data-num="7"></div>
  <div class="block" data-num="8"></div>
  <div class="block" data-num="9"></div>
  <div class="block" data-num="10"></div>
  <div class="block" data-num="11"></div>
  <div class="block" data-num="12"></div>
  <div class="block" data-num="13"></div>
  <div class="block" data-num="14"></div>
  <div class="block" data-num="15"></div>
  <div class="block" data-num="16"></div>
  <div class="block" data-num="17"></div>
  <div class="block" data-num="18"></div>
  <div class="block" data-num="19"></div>
  <div class="block" data-num="20"></div>
  <div class="block" data-num="21"></div>
  <div class="block" data-num="22"></div>
  <div class="block" data-num="23"></div>
  <div class="block" data-num="24"></div>
  <div class="block" data-num="25"></div>
  <div class="block" data-num="26"></div>
  <div class="block" data-num="27"></div>
  <div class="block" data-num="28"></div>
  <div class="block" data-num="29"></div>
  <div class="block" data-num="30"></div>
  <div class="block" data-num="31"></div>
  <div class="block" data-num="32"></div>
  <div class="block" data-num="33"></div>
  <div class="block" data-num="34"></div>
  <div class="block" data-num="35"></div>
  <div class="block" data-num="36"></div>
  <div class="block" data-num="37"></div>
  <div class="block" data-num="38"></div>
  <div class="block" data-num="39"></div>
  <div class="block" data-num="40"></div>
  <div class="block" data-num="41"></div>
  <div class="block" data-num="42"></div>
  <div class="block" data-num="43"></div>
  <div class="block" data-num="44"></div>
  <div class="block" data-num="45"></div>
  <div class="block" data-num="46"></div>
  <div class="block" data-num="47"></div>
  <div class="block" data-num="48"></div>
  <div class="block" data-num="49"></div>
  <div class="block" data-num="50"></div>
  <div class="block" data-num="51"></div>
  <div class="block" data-num="52"></div>
  <div class="block" data-num="53"></div>
  <div class="block" data-num="54"></div>
  <div class="block" data-num="55"></div>
  <div class="block" data-num="56"></div>
  <div class="block" data-num="57"></div>
  <div class="block" data-num="58"></div>
  <div class="block" data-num="59"></div>
  <div class="divider"></div>
</div>
<div class="switch-theme">
  <p>Change theme</p>
  <label class="switch">
    <input type="checkbox" onchange="changeTheme(event)"/><span class="slider"></span>
  </label>
</div>
<script src='//static.codepen.io/assets/common/stopExecutionOnTimeout-b2a7b3fe212eaa732349046d8416e00a9dec26eb7fd347590fbced3ab38af52e.js'></script><script src='https://codepen.io/graphilla/pen/1d0635c13cc2f08cddd7afd9c1222886.js?editors=0010'></script>
<script >const numbers = [
	[1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1], // 0
	[1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1], // 1
	[1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1], // 2
	[1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1], // 3
	[1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1], // 4
	[1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1], // 5
	[1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1], // 6
	[1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0], // 7
	[1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1], // 8
	[1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1]  // 9
];

const blocks = [];
const digits = Array.from(document.querySelectorAll('.block'));

for (let i = 0; i < 4; i++) {
	blocks.push(digits.slice( i * 15, i * 15 + 15 ));
}

const setNum = (block, num) => {
	let n = numbers[num];
	for (let i = 0; i < block.length; i++) {
		 block[i].classList[ n[i] === 1 ?  'add' : 'remove']('active');
	}
};

const time = {
	s: '',
	m: '',
	h: '',
	p: null
};

// time loop
const animator = () => {
	let d = new Date(),
		 h = d.getHours().toString(),
		 m = d.getMinutes().toString(),
		 s = d.getSeconds().toString();
	
	s = s.length === 1 ? '0' + s : s;
	m = m.length === 1 ? '0' + m : m;
	h = h.length === 1 ? '0' + h : h;
	
	if (s !== time.s) {
		for (let i = 0; i < digits.length; i++) {
			let d = digits[i];
			if (i === +s) {
				d.classList.add('second');
				if (time.p !== null)
					digits[time.p].classList.remove('second');
				time.p = i;
				time.s = s;
			}
		}
	}
	
	if (m !== time.m) {
		setNum(blocks[2], m[0]);
		setNum(blocks[3], m[1]);
		time.m = m;
	}
	
	if (h !== time.h) {
		setNum(blocks[0], h[0]);
		setNum(blocks[1], h[1]);
		time.h = h;
	}
 	window.requestAnimationFrame(animator)
}

// init
window.requestAnimationFrame(animator)

// toggle button

const body = document.querySelector('body');
changeTheme = ev => {
	body.classList.toggle('light-theme');
};
//# sourceURL=pen.js
</script>
</body></html>