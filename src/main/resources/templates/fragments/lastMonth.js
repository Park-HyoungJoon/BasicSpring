let lastTarget = document.getElementById("lastMonth");
function lastMonth() {
  let lastTime = new Date();

  let lastMonth = lastTime.getMonth();

  lastTarget.innerText = `${lastMonth}월 특강`
}
lastMonth()


export default lastMonth;