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
function mySearch() {
	var name = $("#search").val();
	if(name!="")
	{
	$.ajax({
		url : "getrestro?name=" + name,
		method : "get",
		success : function(result) {
			$('tr').remove(".rex");
			$("#box").show();
			for(i=0;i<result.length;i++)
			{
			console.log(result[i].userName);
			$('#searchbox').append('<tr class="rex"><td>' + result[i].userName + '</td></tr>');
			}
		},
	    error:function(result){
	    	console.log(result);
	    }
	});
	}
	else{
		$('tr').remove(".rex");
		$("#box").hide();
		
	}
}
