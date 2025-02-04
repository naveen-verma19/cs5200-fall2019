
var genre="technology";
//const arr=[,engineering, technology, business, economics, mathematics];
//biology 430
//medical 300-40



for(var i=0;i<12;i++){
	var para = document.createElement("a"); 
	para.innerText=genre+i;
para.href="https://www.googleapis.com/books/v1/volumes?q=subject:"+genre+"&startIndex="+40*i+"&maxResults=40";
para.setAttribute("download",""+genre+i+".json");
para.setAttribute("class","mine");
document.body.appendChild(para);

para.click();
}



