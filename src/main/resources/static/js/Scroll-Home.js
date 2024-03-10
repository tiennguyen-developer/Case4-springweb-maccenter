
// Created by: Brian Huisman | http://www.greywyvern.com/
// This script downloaded from www.JavaScriptBank.com

/* ********************************************************************
* HTML Block Scroller & Marquee JavaScript - v2.0
*  - Copyright 2008 - Licenced for free distribution under the BSDL
*  -     http://www.opensource.org/licenses/bsd-license.php
*
* Have one or more scrolling blocks of HTML anywhere on your webpages.
* The scroller will even pause on mouseover and resume on mouseout.
*
* Version 2.0
*   - Uses DOM-only methods (compatible with application/xhtml+xml)
*   - Blocks are created in the HTML page, rather than as JS variables
*   - Automatic startup on page load

***********************************************************************
*** Instructions ******************************************************
***********************************************************************
* 1. Create the container for the scroller in the body of your HTML:
*
* <div id="scr1"></div>
*
* Where "scr1" is any name you choose.  You don't have to use a <div>
* either.  You may use any block level element that can be pixel
* resized; such as a <span> element to which the display:block; CSS
* property has been applied.
*
***********************************************************************
* 2. Fill the scroller container with child <div> blocks:
*
* <div id="scr1">
*   <div class="default">
*     The contents of this block will be displayed if the browser does
*     not support the scroller.
*     It will be overwritten if the scroller is supported.
*   </div>
*   <div>Block 2</div>
*   <div>Block 3</div>
*
*   ...
*
* </div>
*
* Assign the class "default" to the first block, the one which you
* would like displayed if javascript is disabled.
*
***********************************************************************
* 3. Add the following rule(s) to your CSS stylesheet:
*
* #scr1 div {
*  visibility:hidden;
* }
* #scr1, #scr1 div.default {
*   width:160px;
*   height:120px;
*   overflow:hidden;
*   visibility:visible;
* }
* #scr1 table tr td div {
*  visibility:visible;
* }
*
* Change both "scr1" to the id you gave to the scroller container.  The
* width and height properties should match those you will use in step 4.
*
* To further style the scroller container, assign CSS properties to the
* scroller target id:
*
* #scr1 {
*   background-color:#f6f6f6;
*   margin:0px auto;
* }
*
* The script will replace each block you add to the container with a
* single-celled <table>. So to style the blocks of your scroller, you
* can style these table cells as if they were actually part of your
* document source:
*
* #scr1 table tr td {
*   padding:10px;
*   color:#ff0000;
*   text-align:center;
*   vertical-align:middle;
* }
*
***********************************************************************
* 4. Create a new scrollObject:
*
* new scrollObject("scr1", 120, 120, "up", 5000, 1.4);
*
* The arguments for this object are as follows:
*  a. - ID of the target tag (from step 1)
*  b. - Width (in pixels) of your scroller
*  c. - Height (in pixels) of your scroller
*  d. - Scroll direction: one of "up", "down", "left" or "right"
*  e. - Amount of time to pause before next scroll begins (ms)
*  f. - Slide-in speed of your scroller (1.001 up to width or height)
*
***********************************************************************
*** To add more scrollers to the same page: ***************************
***********************************************************************
* 1. Create additional containers - with different ID's - and blocks in
*    the body of your HTML
*
* <div id="scr2">
*   <div class="default"><strong>HTML is allowed too!</strong></div>
*   <div><img src="/images/mybanner.jpg" alt=""></div>
*   <div><a href="/home">And links!</a></div>
*   <div>As long as it fits within the dimensions above</div>
* </div>
*
* <div id="scr3">
*   <div class="default">Block 1</div>
*   <div>Block 2</div>
*   <div>Block 3</div>
*   <div>Block 4</div>
* </div>
*
***********************************************************************
* 2. Add the matching rules to your CSS stylesheet
*
* #scr2 div,
* #scr3 div {
*  visibility:hidden;
* }
* #scr2, #scr2 div.default {
*   width:468px;
*   height:60px;
*   overflow:hidden;
*   visibility:visible;
* }
* #scr3, #scr3 div.default {
*   width:140px;
*   height:140px;
*   overflow:hidden;
*   visibility:visible;
* }
* #scr2 table tr td div,
* #scr3 table tr td div {
*  visibility:visible;
* }
*
***********************************************************************
* 3. Create new scrollObjects for each scroller in the <script> tag:
*
* new scrollObject("scr2", 468, 60, "down", 10000, 1.2);
* new scrollObject("scr3", 140, 140, "right", 4000, 2);
*
***********************************************************************
*** End Instructions **************************************************
*************************************************** BEGIN CODE ***** */



/* *****
 * See http://www.greywyvern.com/code/js/scroller.html for the page
 * which uses the example scrollers below
 *
 */


// ***** Start scroller #1
new scrollObject("scr1", 1380, 776, "left", 3500, 1.1);

// ***** Start scroller #2
//new scrollObject("scr2", 468, 60, "left", 3000, 1.5);




/* ********************************************************************
 * The Mighty ScrollObject
 *   - Don't edit this if you know what's good for ya!
 *
 */
function scrollObject(main, width, height, direct, pause, speed) {
  var self = this;
  this.main = main;
  this.width = width;
  this.height = height;
  this.direct = direct;
  this.pause = pause;
  this.speed = Math.max(1.001, Math.min((direct == "up" || direct == "down") ? height : width, speed));
  this.slope = (direct == "up" || direct == "left") ? 1 : -1;
  this.prev = this.offset = 0;
  this.curr = 1;
  this.mouse = false;
  this.scroll = function() {
    this.main = document.getElementById(this.main);
    this.main.style.overflow = "hidden";
    this.main.style.position = "relative";
    this.main.style.width = this.width + "px";
    this.main.style.height = this.height + "px";
    var b = [], c;
    while (this.main.firstChild) if ((c = this.main.removeChild(this.main.firstChild)).nodeName == "DIV") b.push(c);
    for (var x = 0; x < b.length; x++) {
      var table = document.createElement('table');
          table.cellPadding = table.cellSpacing = table.border = "0";
          table.style.position = "absolute";
          table.style.left = table.style.top = "0px";
          table.style.width = table.style.height = "100%";
          table.style.overflow = table.style.visibility = "hidden";
        var tbody = document.createElement('tbody');
          var tr = document.createElement('tr');
            var td = document.createElement('td');
              while (b[x].firstChild)
                  td.appendChild(b[x].removeChild(b[x].firstChild));
              tr.appendChild(td);
            tbody.appendChild(tr);
          table.appendChild(tbody);
      this.main.appendChild(table);
    } b = c = null;
    if (this.main.childNodes.length > 1) {
      this.main.onmouseover = function() { self.mouse = true; };
      this.main.onmouseout = function() { self.mouse = false; };
      setInterval(function() {
        if (!self.offset && self.scrollLoop()) self.main.childNodes[self.curr].style.visibility = "visible";
      }, this.pause);
    } this.main.childNodes[this.prev].style.visibility = "visible";
  };
  this.scrollLoop = function() {
    if (!this.offset) {
      if (this.mouse) return false;
      this.offset = (this.direct == "up" || this.direct == "down") ? this.height : this.width;
    } else this.offset = Math.floor(this.offset / this.speed);
    if (this.direct == "up" || this.direct == "down") {
      this.main.childNodes[this.curr].style.top = (this.offset * this.slope) + "px";
      this.main.childNodes[this.prev].style.top = ((this.offset - this.height) * this.slope) + "px";
    } else {
      this.main.childNodes[this.curr].style.left = (this.offset * this.slope) + "px";
      this.main.childNodes[this.prev].style.left = ((this.offset - this.width) * this.slope) + "px";
    }
    if (!this.offset) {
      this.main.childNodes[this.prev].style.visibility = "hidden";
      this.prev = this.curr;
      if (++this.curr >= this.main.childNodes.length) this.curr = 0;
    } else setTimeout(function() { self.scrollLoop(); }, 30);
    return true;
  };
  if (window.addEventListener) {
    window.addEventListener('load', function() { self.scroll(); }, false); 
  } else if (window.attachEvent)
    window.attachEvent('onload', function() { self.scroll(); });
}



