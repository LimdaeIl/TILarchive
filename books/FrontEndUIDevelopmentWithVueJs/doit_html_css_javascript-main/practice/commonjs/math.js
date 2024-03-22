const perfectScore = 100;

const sum = (number1, number2) => {
    return number1 + number2;
};
const avg = (num1, num2) => {
    return (num1 + num2) / 2;
};

const substract = (num1, num2) => {
    return num1 - num2;
}

module.exports = {
    perfectScore,
    sum,
    avg,
    substract
};