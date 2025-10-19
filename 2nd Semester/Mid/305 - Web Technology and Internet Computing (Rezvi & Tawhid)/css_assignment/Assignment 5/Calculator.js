const display = document.getElementById("display");

function appendInput(value) {
  if (display.value === "0" && value !== ".") {
    display.value = value;
  } else {
    display.value += value;
  }
}

function clearDisplay() {
  display.value = "";
}

function deleteLast() {
  display.value = display.value.toString().slice(0, -1);
}

function calculateResult() {
  try {
    const expression = display.value.replace(/×/g, "*");

    const result = new Function("return " + expression)();

    display.value = Number(result.toFixed(10));
  } catch (error) {
    display.value = "Error";
  }
}
