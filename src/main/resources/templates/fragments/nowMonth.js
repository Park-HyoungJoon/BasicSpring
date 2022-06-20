let Target = document.getElementById("myMonth");
function myMonth() {
  let time = new Date();

  let month = time.getMonth();

  Target.innerText = `${month + 1}월 특강`
}
myMonth();

export default nowMonth;