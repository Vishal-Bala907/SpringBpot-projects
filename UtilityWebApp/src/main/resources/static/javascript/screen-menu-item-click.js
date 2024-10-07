const menuItem = document.getElementById("menuButton");
const navlinks = document.getElementsByClassName("navlinks");
let menuItemClickCount = 0;
let Myheight = 3;

window.addEventListener('resize', function(event) {
	if (window.innerWidth <= 800) {
		navlinks[0].style.visibility = "hidden"
		navlinks[1].style.visibility = "hidden"
		document.getElementsByClassName('header')[0].style.height = 3 + "em";
	}
	 else if (window.innerWidth >801 && window.innerWidth < 1501) {
		navlinks[0].style.visibility = "visible"
		navlinks[1].style.visibility = "visible"
		document.getElementsByClassName('header')[0].style.height = 3 + "em";
	}
	
	 else if (window.innerWidth > 1501) {
		navlinks[0].style.visibility = "visible"
		navlinks[1].style.visibility = "visible"
		document.getElementsByClassName('header')[0].style.height = 3 + "em";
	}
});

const menuItemfunction = () => {
	const header = document.getElementsByClassName('header')[0]
	var id = null;
	clearInterval(id);
	console.log(Myheight)
	if (Myheight >= 3 && Myheight < 15) {
		id = setInterval(function() {

			if (Myheight >= 15) {
				clearInterval(id)
				menuItem.style.transform = 'rotate(' + 90 + 'deg)';
				navlinks[0].style.visibility = "visible"
				navlinks[1].style.visibility = "visible"
			} else {
				header.style.height = Myheight + "em";
				Myheight += .1;
			}

		}, 5);
	} else if (Myheight >= 15) {
		id = setInterval(function() {
			navlinks[0].style.visibility = "hidden"
			navlinks[1].style.visibility = "hidden"
			if (Myheight <= 3.1) {
				clearInterval(id)
				menuItem.style.transform = 'rotate(' + 180 + 'deg)';
			} else {
				header.style.height = Myheight + "em";
				Myheight -= .1;
			}

		}, 1);

	}

}

menuItem.addEventListener("click", menuItemfunction)
// Toggeling b/w login and singup
loginSignupButton = document.getElementById("login-signup-button");
signupLoginButton = document.getElementById("signup-login-button");

loginSignupButton.addEventListener("click",()=>{
	document.getElementsByClassName('login')[0].style.visibility = "hidden";
	document.getElementsByClassName('sign-up')[0].style.visibility = "visible";
})

signupLoginButton.addEventListener("click",()=>{
	document.getElementsByClassName('login')[0].style.visibility = "visible";
	document.getElementsByClassName('sign-up')[0].style.visibility = "hidden";
})
