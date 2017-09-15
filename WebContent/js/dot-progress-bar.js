//Uses jQuery because it was quick. You'll want to write something that works within the context of your app.

$("#advance").on("click", function() {
  var $bar = $(".ProgressBar");
  var $accept = $("#accept");
  if ($bar.children(".is-current").length > 0) {
    $bar.children(".is-current").removeClass("is-current").next().addClass("is-current is-complete");
    $("#accept").html("<h3>已完成</h3>");
    $("#process").html("100%");
  } else {
    $bar.children().first().addClass("is-current is-complete");
  }
});

$("#previous").on("click", function() {
  var $bar = $(".ProgressBar");
  if ($bar.children(".is-current").length > 0) {
    $bar.children(".is-current").removeClass("is-current").prev().removeClass("is-complete").addClass("is-current");
  } else {
    $bar.children(".is-complete").last().removeClass("is-complete").addClass("is-current");
  }
});

/*
$("#advance").on("click", function() {
  var $bar = $(".ProgressBar");
  if ($bar.children(".is-current").length > 0) {
    $bar.children(".is-current").removeClass("is-current").next().addClass("is-current is-complete");
    
  } else {
    $bar.children().first().addClass("is-current is-complete");
  }
});
*/