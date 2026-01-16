const display = document.getElementById("display");
const calculator = document.querySelector(".calculator");
const buttonContainer = document.getElementById("button-container");
let isCalculated = false;

const modes = {
  normal: [
    { text: "C", cls: "clear", fn: "clearDisplay()" },
    { text: "DEL", cls: "delete", fn: "deleteLast()" },
    { text: "/", cls: "operator", fn: "appendInput('/')" },
    { text: "×", cls: "operator", fn: "appendInput('*')" },
    { text: "7", cls: "number", fn: "appendInput('7')" },
    { text: "8", cls: "number", fn: "appendInput('8')" },
    { text: "9", cls: "number", fn: "appendInput('9')" },
    { text: "-", cls: "operator", fn: "appendInput('-')" },
    { text: "4", cls: "number", fn: "appendInput('4')" },
    { text: "5", cls: "number", fn: "appendInput('5')" },
    { text: "6", cls: "number", fn: "appendInput('6')" },
    { text: "+", cls: "operator", fn: "appendInput('+')" },
    { text: "1", cls: "number", fn: "appendInput('1')" },
    { text: "2", cls: "number", fn: "appendInput('2')" },
    { text: "3", cls: "number", fn: "appendInput('3')" },
    { text: "=", cls: "equal", fn: "calculateResult()" },
    { text: "0", cls: "zero", fn: "appendInput('0')" },
    { text: ".", cls: "number", fn: "appendInput('.')" },
  ],

  trig: [
    { text: "C", cls: "clear", fn: "clearDisplay()" },
    { text: "DEL", cls: "delete", fn: "deleteLast()" },
    { text: "sin", cls: "operator", fn: "appendFunction('sin')" },
    { text: "cos", cls: "operator", fn: "appendFunction('cos')" },
    { text: "tan", cls: "operator", fn: "appendFunction('tan')" },
    { text: "π", cls: "number", fn: "appendInput('Math.PI')" },
    { text: "(", cls: "operator", fn: "appendInput('(')" },
    { text: ")", cls: "operator", fn: "appendInput(')')" },
    { text: "7", cls: "number", fn: "appendInput('7')" },
    { text: "8", cls: "number", fn: "appendInput('8')" },
    { text: "9", cls: "number", fn: "appendInput('9')" },
    { text: "/", cls: "operator", fn: "appendInput('/')" },
    { text: "4", cls: "number", fn: "appendInput('4')" },
    { text: "5", cls: "number", fn: "appendInput('5')" },
    { text: "6", cls: "number", fn: "appendInput('6')" },
    { text: "*", cls: "operator", fn: "appendInput('*')" },
    { text: "1", cls: "number", fn: "appendInput('1')" },
    { text: "2", cls: "number", fn: "appendInput('2')" },
    { text: "3", cls: "number", fn: "appendInput('3')" },
    { text: "-", cls: "operator", fn: "appendInput('-')" },
    { text: "0", cls: "zero", fn: "appendInput('0')" },
    { text: ".", cls: "number", fn: "appendInput('.')" },
    { text: "=", cls: "equal", fn: "calculateResult()" },
    { text: "+", cls: "operator", fn: "appendInput('+')" },
  ],

  scientific: [
    { text: "C", cls: "clear", fn: "clearDisplay()" },
    { text: "DEL", cls: "delete", fn: "deleteLast()" },
    { text: "√", cls: "operator", fn: "appendFunction('sqrt')" },
    { text: "x²", cls: "operator", fn: "appendFunction('square')" },
    { text: "log", cls: "operator", fn: "appendFunction('log')" },
    { text: "(", cls: "operator", fn: "appendInput('(')" },
    { text: ")", cls: "operator", fn: "appendInput(')')" },
    { text: "+", cls: "operator", fn: "appendInput('+')" },
    { text: "7", cls: "number", fn: "appendInput('7')" },
    { text: "8", cls: "number", fn: "appendInput('8')" },
    { text: "9", cls: "number", fn: "appendInput('9')" },
    { text: "/", cls: "operator", fn: "appendInput('/')" },
    { text: "4", cls: "number", fn: "appendInput('4')" },
    { text: "5", cls: "number", fn: "appendInput('5')" },
    { text: "6", cls: "number", fn: "appendInput('6')" },
    { text: "*", cls: "operator", fn: "appendInput('*')" },
    { text: "1", cls: "number", fn: "appendInput('1')" },
    { text: "2", cls: "number", fn: "appendInput('2')" },
    { text: "3", cls: "number", fn: "appendInput('3')" },
    { text: "-", cls: "operator", fn: "appendInput('-')" },
    { text: "0", cls: "zero", fn: "appendInput('0')" },
    { text: ".", cls: "number", fn: "appendInput('.')" },
    { text: "=", cls: "equal", fn: "calculateResult()" },
    { text: "π", cls: "number", fn: "appendInput('Math.PI')" },
    { text: "e", cls: "number", fn: "appendInput('Math.E')" },
  ],
};

function generateButtons(mode) {
  buttonContainer.innerHTML = "";
  modes[mode].forEach((btn) => {
    const button = document.createElement("button");
    button.className = `btn ${btn.cls}`;
    button.innerHTML = btn.text;
    button.setAttribute("onclick", btn.fn);
    buttonContainer.appendChild(button);
  });
}

function changeMode() {
  const mode = document.getElementById("mode").value;
  generateButtons(mode);
  clearDisplay();
}

function appendInput(value) {
  if (isCalculated && !["+", "-", "*", "/", ")", "("].includes(value)) {
    display.value = value;
    isCalculated = false;
  } else {
    display.value += value;
    isCalculated = false;
  }
}

function appendFunction(func) {
  switch (func) {
    case "sin":
      display.value += "Math.sin(";
      break;
    case "cos":
      display.value += "Math.cos(";
      break;
    case "tan":
      display.value += "Math.tan(";
      break;
    case "sqrt":
      display.value += "Math.sqrt(";
      break;
    case "square":
      display.value += "**2";
      break;
    case "log":
      display.value += "Math.log10(";
      break;
    case "exp":
      display.value += "Math.exp(";
      break;
  }
  isCalculated = false;
}

function clearDisplay() {
  display.value = "";
  isCalculated = false;
}

function deleteLast() {
  display.value = display.value.toString().slice(0, -1);
  isCalculated = false;
}

function getRandomColor() {
  const letters = "0123456789ABCDEF";
  let color = "#";
  for (let i = 0; i < 6; i++) {
    color += letters[Math.floor(Math.random() * 16)];
  }
  return color;
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
  document.body.style.background = `linear-gradient(135deg, ${color1}, ${color2})`;
}

generateButtons("normal");
