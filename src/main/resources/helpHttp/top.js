function shineLinks(id){
  try{
    var el=document.getElementById(id).getElementsByTagName('a');
	var url=document.location.href;
	for(var i=0;i<el.length; i++){
	  if (url==el[i].href){ el[i].className = 'active';};
	};
  }catch(e){}
};

document.getElementById("General").innerHTML += ("<div><H1>Help</H1></div>"+
      "<div><p>Akka-http based WEB service</p></div>");
document.getElementById('menu').innerHTML += ("<ul>"+
      "<li><a href=\"/helpHttp/index.html\">Index</a></li>" +
      "</ul>");
shineLinks("menu")