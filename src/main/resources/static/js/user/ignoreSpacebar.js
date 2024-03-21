				document.addEventListener('DOMContentLoaded', function () {
					var inputs = document.querySelectorAll('input');
					inputs.forEach(function (input) {
						input.addEventListener('keydown', function (event) {
							if (event.key === ' ' || event.keyCode === 32) {
								event.preventDefault();
								return false;
							}
						});
					});
				});