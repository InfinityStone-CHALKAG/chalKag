let filesArray = [];

document.getElementById('customButton').addEventListener('click', function() {
    document.getElementById('fileInput').click();
});

const bigImage = document.getElementById('big-image');

// 슬라이드 버튼의 가시성을 업데이트하는 함수
function updateSliderButtonsVisibility() {
    const imageContainer = document.querySelector('.image-container');
    const slideRightButton = document.getElementById('slideRight');
    const slideLeftButton = document.getElementById('slideLeft');
    const imagesCount = imageContainer.querySelectorAll('img').length;
    const preview = document.getElementById('preview');

    // 이미지가 하나 이상 있으면 슬라이드 버튼을 보이게 하고, 아니면 숨긴다.
    if (imagesCount > 0) {
        slideRightButton.style.display = 'block';
        slideLeftButton.style.display = 'block';
        preview.style.marginBottom = '4%';
    } else {
        slideRightButton.style.display = 'none';
        slideLeftButton.style.display = 'none';
    }
}

document.getElementById("fileInput").addEventListener('change', function(e) {
    const imageContainer = document.querySelector('.image-container');
    // 이전에 업로드된 이미지와 현재 선택된 파일의 총 개수를 확인
    const existingImages = imageContainer.querySelectorAll('img').length;
    const selectedFiles = e.target.files;
    const totalImages = existingImages + selectedFiles.length;

    if (totalImages > 10) {
		swal("fail", "'최대 10장의 이미지만 업로드할 수 있습니다.'", "error", {
         button: "OK",
      	});
        return; // 추가 이미지 업로드 방지
    }

    // 선택된 모든 파일을 처리
    const files = e.target.files;
    for (const file of selectedFiles) {
        // 파일 크기 제한 확인
    if (file.size > 30 * 1024 * 1024) {
		swal("fail", "파일 크기는 30MB를 초과할 수 없습니다.", "error", {
         button: "OK",
      	});
            continue; // 현재 파일 처리를 건너뛰고 다음 파일로 넘어감
        }

        // 파일 타입 확인
        if (!file.type.match('image.*')) {
		swal("fail", "'이미지 파일만 업로드할 수 있습니다.'", "error", {
        	 button: "OK",
      		});
            continue; // 현재 파일 처리를 건너뛰고 다음 파일로 넘어감
        }
        
        const reader = new FileReader();

        reader.onload = function(e) {
			const imgWrapper = document.createElement('div'); // 이미지와 삭제 버튼을 포함할 div
            imgWrapper.classList.add('img-wrapper');
			
            const img = document.createElement('img');
            img.id = 'previewImg';
            img.src = e.target.result;

            imageContainer.appendChild(img);
            
            img.onclick = function() {
				    bigImage.style.display = 'block';
		        document.getElementById('big-image').src = e.target.result;
		    };
            
            const deleteButton = document.createElement('button');
            deleteButton.textContent = 'X';
            deleteButton.classList.add('delete-btn');
            deleteButton.type = 'button'; 
            
            deleteButton.onclick = function() {
				 // 이미지가 추가될 때마다 슬라이드 버튼 가시성 업데이트
                imgWrapper.remove(); // 해당 이미지를 DOM에서 제거
                // filesArray에서 이 파일을 제거
                const index = filesArray.indexOf(file);
                if (index > -1) {
                    filesArray.splice(index, 1);
                }
                updateSliderButtonsVisibility(); // 슬라이드 버튼 가시성 업데이트
            };

             // 순서 변경 버튼 추가
             const upButton = document.createElement('button');
             upButton.textContent = '◀';
             upButton.classList.add('move-up-btn');
             upButton.type = 'button';
             upButton.onclick = function() {
                 // 현재 이미지를 위로 이동
                 if (imgWrapper.previousElementSibling) {
                     imageContainer.insertBefore(imgWrapper, imgWrapper.previousElementSibling);
                     // 이미지 순서를 변경하면 업로드 되는 순서도 변경
					 const index = filesArray.indexOf(file);
			         if (index > 0) {
			            const temp = filesArray[index];
			            filesArray[index] = filesArray[index - 1];
			            filesArray[index - 1] = temp;
			        }
                 }
             };

            const downButton = document.createElement('button');
            downButton.textContent = '▶';
            downButton.classList.add('move-down-btn');
            downButton.type = 'button';
            downButton.onclick = function() {
                // 현재 이미지를 아래로 이동
                if (imgWrapper.nextElementSibling) {
                    imageContainer.insertBefore(imgWrapper.nextElementSibling, imgWrapper);
					// filesArray 내의 파일 순서를 변경
				    const index = filesArray.indexOf(file);
				    if (index > -1 && index < filesArray.length - 1) {
				        const temp = filesArray[index];
				        filesArray[index] = filesArray[index + 1];
				        filesArray[index + 1] = temp;
				    }
                }
            };

			imgWrapper.appendChild(img);
            imgWrapper.appendChild(deleteButton);
            // 순서 변경 버튼을 imgWrapper에 추가
            imgWrapper.appendChild(upButton);
            imgWrapper.appendChild(downButton);
            imageContainer.appendChild(imgWrapper);
            
            // 선택된 파일을 filesArray에 추가
        	filesArray.push(file);

            updateSliderButtonsVisibility();
        };

        reader.readAsDataURL(file);
    }
});

let scrollAmount = 0; // 슬라이더의 현재 위치를 추적하는 변수 초기화

document.getElementById('slideLeft').addEventListener('click', function(event) {
    event.preventDefault(); // 기본 동작 막기
    
    const slider = document.querySelector('.image-container');
    
    // 현재 translateX 값을 가져오기 위해 정규식 사용
    const currentTransform = slider.style.transform.match(/translateX\((-?\d+)px\)/);
    let currentTranslateX = currentTransform ? parseInt(currentTransform[1]) : 0;
    
    // 왼쪽으로 슬라이드하기 위해 scrollAmount를 증가시키되, 0보다 크지 않도록 함
    if (currentTranslateX < 0) {
        currentTranslateX = Math.min(currentTranslateX + 100, 0);
        slider.style.transform = `translateX(${currentTranslateX}px)`;
    }
});

document.getElementById('slideRight').addEventListener('click', function(event) {
    event.preventDefault(); // 기본 동작 막기
    
    const slider = document.querySelector('.image-container');
    const sliderWidth = slider.scrollWidth; // 슬라이더의 전체 너비
    const containerWidth = slider.offsetWidth; // 보이는 컨테이너의 너비
    
    // 현재 translateX 값을 가져오기 위해 정규식 사용
    const currentTransform = slider.style.transform.match(/translateX\((-?\d+)px\)/);
    let currentTranslateX = currentTransform ? parseInt(currentTransform[1]) : 0;
    
    // 슬라이드 가능한 최대 양 계산
    const maxScrollAmount = containerWidth - sliderWidth;
    
    // 오른쪽으로 슬라이드하기 위해 scrollAmount를 감소시키되, 최대 슬라이드 양을 초과하지 않도록 함
    if (currentTranslateX > maxScrollAmount) {
        currentTranslateX = Math.max(currentTranslateX - 100, maxScrollAmount);
        slider.style.transform = `translateX(${currentTranslateX}px)`;
    }
});

// // 초기 페이지 로드 시 슬라이드 버튼 가시성을 업데이트.
document.addEventListener('DOMContentLoaded', function() {
    updateSliderButtonsVisibility();
});