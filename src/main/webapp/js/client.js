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
	console.log(name);
	if(name!="")
	{
	$.ajax({
		url : "get?restro="+name,
		method : "get",
		success : function(result) {
			$('tr').remove(".rex");
			$("#box").show();
			for(i=0;i<result.length;i++)
			{
			console.log(result[i]);
			$('#searchbox').append('<tr class="rex"><td><a href="getRestro?id='+result[i].id+'" class="btn">' + result[i].restro + '</td></tr>');
			}
		},
	    error:function(result){
	    	console.log("data")
	    	console.log(result);
	    }
	});
	}
	else{
		$('tr').remove(".rex");
		$("#box").hide();
		
	}
}
function add(quantity){
	
	 quan = $("#"+quantity.id+"").val();
	 $("#"+quantity.id+"").val(parseInt(quan)+1);
	
}
function sub(quantity){
	 quan = $("#"+quantity.id+"").val();
	 if(quan!=0)
	 $("#"+quantity.id+"").val(parseInt(quan)-1);
	
}

