

let el = document.querySelector(".input-number");
let input = el.querySelector("input");
let incrementBtn = el.querySelector("button.input-number-increment");
let decrementBtn = el.querySelector("button.input-number-decrement");

// minimum number
let min = 0;

//set the value
input.value = min;

//increment
incrementBtn.addEventListener("click", function () {
  var value = parseInt(input.value);
  if (value >= min) {
    value++;
  }
  input.value = value;
});

//decrement
decrementBtn.addEventListener("click", function () {
  var value = parseInt(input.value);
  if (value > min) {
    value--;
  }
  input.value = value;
});

//blur if the length is less than min
input.addEventListener("blur", function () {
  if (input.value.length === 0 || Math.sign(input.value) == -1) {
    input.value = min;
  }
});


function myFunction(x) {
  x.classList.toggle("change");
  var x = document.getElementById("menu-item");
  if (x.style.display === "none") {
    x.style.display = "block";

  } else {
    x.style.display = "none";
  }
} 