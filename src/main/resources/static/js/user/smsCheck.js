 function createMemberPhCheckInput(containerId) {
                    var container = document.getElementById(containerId); // 동적으로 컨테이너 선택

                    // 기존에 생성된 입력란과 버튼이 있다면 제거
                    container.innerHTML = '';

                    // 인증번호 입력란 생성
                    var input = document.createElement("input");
                    input.type = "text";
                    input.id = "memberPhCheck"; // 고유 ID 부여
                    input.name = "memberPhCheck";
                    input.className = "form-control";
                    input.placeholder = "인증번호 입력";

                    // 확인 버튼 생성
                    var button = document.createElement("a");
                    button.id = containerId + "Button"; // 고유 ID 부여
                    button.className = "btn btn-magz btn-sm";
                    button.style.textAlign = "center";
                    button.style.paddingTop = "3%";
                    button.style.width = "7rem";
                    button.textContent = "check";

                    // 생성된 요소를 부모 컨테이너에 추가
                    container.appendChild(input);
                    container.appendChild(button);

                    // 컨테이너 표시
                    container.style.display = "flex";
                    console.log("[로그] input.id : " + input.id);




                    //SMS check.js

                    $(document).ready(function () {
                        $("#" + button.id).on('click', function () {
                            console.log("smsCheck 동작함");
                            console.log(serverGeneratedCode);
                            if ($("#" + input.id).val() == serverGeneratedCode) {
                                $("#" + input.id).prop('disabled', true);
                                $(".successPhCheck").text("인증번호가 일치합니다.");
                                $(".successPhCheck").css("color", "green");
                                checkPhFlag = true;
                                return checkPhFlag;
                            } else {
                                $(".successPhCheck").text("인증번호가 일치하지 않습니다. 확인해주시기 바랍니다.");
                                $(".successPhCheck").css("color", "red");
                                $(this).attr("autofocus", true);
                                checkPhFlag = false;
                                return checkPhFlag;

                            }
                        });
                    });
                }