function send() {
	let email = $("#email").val();
	console.log("Amit");
	$("#result").html("");
	$.ajax({
		url : "../sendotp?email=" + email,
		success : function() {
			$("#result").html("Otp Sended Successfully");
		},
		error : function(result) {
			$("#result").html("Something Went Wrong while sending otp");
		}
	});
}
function login() {
	let email = $("#email").val();
	let password = $("#password").val();
	$.ajax({
		url : "../login?email=" + email + "&password=" + password,
		success : function() {
			$("#result").html("Otp Sended Successfully");
		},
		error : function(result) {
			$("#result").html("Invalid credentials");
		}
	});
}
function submitForm() {
	$("#clientSignUp").submit();
}
