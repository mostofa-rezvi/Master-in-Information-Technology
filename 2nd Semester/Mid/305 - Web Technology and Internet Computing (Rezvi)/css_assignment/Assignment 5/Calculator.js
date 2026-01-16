const display = document.getElementById("display");
const calculator = document.querySelector(".calculator");
let isCalculated = false;

function getRandomColor() {
  const letters = "0123456789ABCDEF";
  let color = "#";
  for (let i = 0; i < 6; i++) {
    color += letters[Math.floor(Math.random() * 16)];
  }
  return color;
}

function appendInput(value) {
  if (isCalculated && !["+", "-", "*", "/"].includes(value)) {
    display.value = value;
    isCalculated = false;
  } else {
    if (display.value === "0" && value !== ".") {
      display.value = value;
    } else {
      display.value += value;
    }
    isCalculated = false;
  }
}

function clearDisplay() {
  display.value = "";
  isCalculated = false;
}

function deleteLast() {
  display.value = display.value.toString().slice(0, -1);
  isCalculated = false;
}

function calculateResult() {
  try {
    const expression = display.value.replace(/×/g, "*");
    const result = new Function("return " + expression)();
    display.value = Number(result.toFixed(10));
    isCalculated = true;
  } catch (error) {
    display.value = "Error";
    isCalculated = true;
  }

  const color1 = getRandomColor();
  const color2 = getRandomColor();

  // calculator.style.backgroundColor = getRandomColor();

  document.body.style.background = `linear-gradient(135deg, ${color1}, ${color2})`;
}
