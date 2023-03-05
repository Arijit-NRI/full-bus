
/* 

    @author: Arijit Saha
	@desc: js for all the html files 
	@file path from root: Ex ./index.js/

*/
// var cors = require('cors');
var nrifintech="NRI TRAVEL";
var booking = `My Bookings`; 
var logout="Logout";
var home="Home";

// document.getElementById("nrifintech").innerHTML=nrifintech;
// document.getElementById("booking").innerHTML = booking; 
// document.getElementById("logout").innerHTML = logout;

document.querySelector(".nrifintech").innerHTML=nrifintech;
document.querySelector(".booking").innerHTML=booking;
document.querySelector(".logout").innerHTML=logout;
// document.querySelector(".home").innerHTML=home;

function togglepassword() {
	var x = document.getElementById("mypassword");
	var y=document.getElementById("mypassword-icon");
	if (x.type === "password") {
		
	  	
		y.src="./public/hide-eye.png";
	  x.type = "text";
	} else {
	  x.type = "password";
	  y.src="./public/eye-icon.svg";
	  y.class="hidden-eye";
	}
}

function on() {
	// document.getElementById("overlay").style.display = "block";
	document.querySelector(".cancel-overlay").style.display="block";
}
  
function off() {
	// document.getElementById("overlay").style.display = "none";
	document.querySelector(".cancel-overlay").style.display="none";
}

function onbooking()
{
	document.querySelector(".booking-cancel-overlay").style.display="block";
}
function offbooking()
{
	document.querySelector(".booking-cancel-overlay").style.display="none";
}

function onroute()
{
	document.querySelector(".route-overlay").style.display="block";
}
function offroute()
{
	document.querySelector(".route-overlay").style.display="none";
}
function homeonroute()
{
	document.querySelector(".home-route-overlay").style.display="block";
}
function homeoffroute()
{
	document.querySelector(".home-route-overlay").style.display="none";
}

/*Fetching functions*/
 //var fromelement ="";
function getfrom()
{
	selectedElementFrom=document.querySelector(".from-id");
	frontelement = selectedElementFrom.options[selectedElementFrom.selectedIndex].value;
	console.log("Got from value"+ frontelement);
	return frontelement;
}

//var toelement ="";
function getto()
{
	selectedElementTo=document.querySelector(".to-id");
	toelement = selectedElementTo.options[selectedElementTo.selectedIndex].value;
	console.log("Got to value"+toelement);
	return toelement;
}

/* Trying to fetch data*/

const url='http://localhost:3030/api/v1/destination/get';
fetch(url, {
		method : "GET",
	}).then(res=>{
		return res.json();
	}).then(data=>{
		data.forEach(user=>{
			// const markup= `<h1> ${user.name} </h1> `;
			const markup=`<option value=" ${user.name} "> ${user.name} </option>`;
			// <option value="option1">Option1</option>
			// document.querySelector(".from-id").insertAdjacentHTML('afterbegin',markup);

			// document.querySelector(".from-id").value

			// if(  `${user.name}`!= getfrom()  )
			// {
				//console.log(`${user.name}`+" "+ getfrom() );
				document.querySelector(".to-id").insertAdjacentHTML('beforeend',markup);
			// }
			//  if( getto() != `${user.name}` ) 
			//  {
				
				document.querySelector(".from-id").insertAdjacentHTML('beforeend',markup);
			//  }
			// document.querySelector(".from-id").insertAdjacentHTML('beforeend',markup);
			// document.querySelector(".to-id").insertAdjacentHTML('beforeend',markup);


			// document.querySelector(".from-id").insertAdjacentHTML('afterbegin',markup);
			// insertAdjacentElement('beforeend',markup);
		});
	})
	.catch(error=>console.error(error));


