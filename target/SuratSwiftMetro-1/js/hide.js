
$("#linkRegister").on("click",function(e){
    e.preventDefault();
    $("#login").hide();
    $(".box").hide();
    $(".regBox").show();
    $("#register").show();
});
$("#linkLogin").on("click",function(e){
    e.preventDefault();
    $("#login").show();
    $(".box").show();
    $("#register").hide();
    $(".regBox").hide();
});