/* 
    @author: Monidepa Ghosh
	@desc: js for index.html (admin site)
	@file path from root: Ex ./index.js/
*/

var nrifintech="NRI TRAVEL";
// var booking = `My Bookings`; 
var logout="Logout";

document.querySelector(".nrifintech").innerHTML=nrifintech;
// document.querySelector(".booking").innerHTML=booking;
document.querySelector(".logout").innerHTML=logout;

function openSidebar() {
	console.log("OPEN")
	document.getElementById("mySidebar").style.display = "block";
  }
  
  function closeSidebar() {
	document.getElementById("mySidebar").style.display = "none";
  }


  function firstdiv(){
	document.getElementById("firstdiv").style.display = "block";
	document.getElementById("seconddiv").style.display = "none";
	document.getElementById("thirddiv").style.display = "none";
	document.getElementById("fourthdiv").style.display = "none";
	document.getElementById("fifthdiv").style.display = "none";
	document.getElementById("text1").style.color="red";
	document.getElementById("text2").style.color="black";
	document.getElementById("text3").style.color="black";
	document.getElementById("text4").style.color="black";
	document.getElementById("text5").style.color="black";
  }

  function seconddiv(){
	document.getElementById("firstdiv").style.display = "none";
	document.getElementById("seconddiv").style.display = "block";
	document.getElementById("thirddiv").style.display = "none";
	document.getElementById("fourthdiv").style.display = "none";
	document.getElementById("fifthdiv").style.display = "none";
	document.getElementById("text1").style.color="black";
	document.getElementById("text2").style.color="red";
	document.getElementById("text3").style.color="black";
	document.getElementById("text4").style.color="black";
	document.getElementById("text5").style.color="black";
  }

  function thirddiv(){
	document.getElementById("firstdiv").style.display = "none";
	document.getElementById("seconddiv").style.display = "none";
	document.getElementById("thirddiv").style.display = "block";
	document.getElementById("fourthdiv").style.display = "none";
	document.getElementById("fifthdiv").style.display = "none";
	document.getElementById("text1").style.color="black";
	document.getElementById("text2").style.color="black";
	document.getElementById("text3").style.color="red";
	document.getElementById("text4").style.color="black";
	document.getElementById("text5").style.color="black";
  }

  function fourthdiv(){
	document.getElementById("firstdiv").style.display = "none";
	document.getElementById("seconddiv").style.display = "none";
	document.getElementById("thirddiv").style.display = "none";
	document.getElementById("fourthdiv").style.display = "block";
	document.getElementById("fifthdiv").style.display = "none";
	document.getElementById("text1").style.color="black";
	document.getElementById("text2").style.color="black";
	document.getElementById("text3").style.color="black";
	document.getElementById("text4").style.color="red";
	document.getElementById("text5").style.color="black";
  }

  function fifthdiv(){
	document.getElementById("firstdiv").style.display = "none";
	document.getElementById("seconddiv").style.display = "none";
	document.getElementById("thirddiv").style.display = "none";
	document.getElementById("fourthdiv").style.display = "none";
	document.getElementById("fifthdiv").style.display = "block";
	document.getElementById("text1").style.color="black";
	document.getElementById("text2").style.color="black";
	document.getElementById("text3").style.color="black";
	document.getElementById("text4").style.color="black";
	document.getElementById("text5").style.color="red";
  }


//   $(document).ready(function() {
// 	var fieldIndex = 1;
// 	$(".add-field").on("click", function(event) {
// 		event.preventDefault();
// 	// Create a new row with two text fields
// 	var newRow = "<tr><td><input type='text' name='name'></td><td><input type='text' name='email'></td></tr>";
	
// 	// Append the new row to the table body
// 	$(".container").append(newRow);
// 	});
//   });



  function on() {
	document.querySelector(".search-overlay").style.display="block";
}

function off() {
	document.querySelector(".search-overlay").style.display="none";
}

function on2() {
	document.querySelector(".bus-overlay").style.display="block";
}

function off2() {
	document.querySelector(".bus-overlay").style.display="none";
}

function on3() {
	document.querySelector(".destination-overlay").style.display="block";
}

function off3() {
	document.querySelector(".destination-overlay").style.display="none";
}


function add_field() {
	var table = document.querySelector(".container");
	var row = table.insertRow(-1);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);

	var div1 = document.querySelector(".from");
	var newDiv1 = document.createElement("div");
	newDiv1.innerHTML = div1.innerHTML;
	cell1.appendChild(newDiv1);

	var div2 = document.querySelector(".to");
	var newDiv2 = document.createElement("div");
	newDiv2.innerHTML = div2.innerHTML;
	cell2.appendChild(newDiv2);


  }
  



  /*Arijit*/


  const employeeForm = document.getElementById('employee-form');
employeeForm.addEventListener('submit', (event) => {
  event.preventDefault(); // prevent the default form submission
  const name = document.getElementById('name').value;
  const email = document.getElementById('email').value;
  const password = document.getElementById('password').value;
  const formData = {
    email,
    name,
    password
  };
  console.log(formData);

  try {
    fetch('http://localhost:8080/api/v1/user/create', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(formData),
    })
    .then(response => {
      if (!response.ok) {
        return response.text().then(message => {
          throw new Error(message);
        });
      } else {
        alert('Employee added successfully');
		document.getElementById('name').value="";
		document.getElementById('password').value="";
		document.getElementById('email').value="";
        return response.json();
      }
    })
    .then(data => {
      console.log(data);
    })
    .catch(error => {
      alert('Error: ' + error.message);
    });
  } catch (ex) {
    alert('Error: ' + ex.message);
  }
});

window.onload = () => {
	fetch('http://localhost:8080/api/v1/user/get')
	  .then(response => {
		if (!response.ok) {
		  throw new Error(response.statusText);
		}
		return response.json();
	  })
	  .then(data => {
		const table = document.getElementById('employee-table');
		const tbody = table.getElementsByTagName('tbody')[0];
  
		// loop through the data and create a row for each employee
		data.forEach(employee => {
		  const row = document.createElement('tr');
		  const idCell = document.createElement('td');
		  const nameCell = document.createElement('td');
		  const emailCell = document.createElement('td');
		  const passwordCell = document.createElement('td');
  
		  idCell.innerText = employee.id;
		  nameCell.innerText = employee.name;
		  emailCell.innerText = employee.email;
		  passwordCell.innerText = employee.password;
  
		  row.appendChild(idCell);
		  row.appendChild(nameCell);
		  row.appendChild(emailCell);
		  row.appendChild(passwordCell);
  
		  tbody.appendChild(row);
		});
	  })
	  .catch(error => {
		alert(`Error fetching employee data: ${error}`);
	  });
  };
  
  
