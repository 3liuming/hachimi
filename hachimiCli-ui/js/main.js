(function () {
  // 用 const 保存在当前作用域，避免被外部覆盖
  const nativeFetch = window.fetch;
  if (!nativeFetch || nativeFetch === window.fetch.__patched) {
    // 如果已经被 patch 过，直接退出
    return;
  }

  window.fetch = async function (input, init = {}) {
    const token = localStorage.getItem('hachimitoken');

    init.headers = {
      ...init.headers,
      ...(token ? { Authorization: 'Bearer ' + token } : {}),
    };

    // 自动 JSON 化
    if (
      init.body &&
      (init.method === 'PUT' || init.method === 'POST') &&
      typeof init.body === 'object' &&
      !(init.body instanceof FormData) &&
      !(init.body instanceof Blob)
    ) {
      init.body = JSON.stringify(init.body);
      init.headers['Content-Type'] = 'application/json';
    }

    const response = await nativeFetch(input, init);

    if (response.status === 401) {
      const clone = response.clone();
      try {
        const data = await clone.json();
        if (data.error === 'Token has expired') {
          if (!['/login.html', '/register.html', '/index.html'].includes(location.pathname)) {
            alert('您的登录已过期，请重新登录');
            localStorage.removeItem('hachimitoken');
            location.href = '/login.html';
          }
        }
      } catch (e) {
        // 忽略解析错误
      }
    }

    return response;
  };

  // 打标记，防止重复 patch
  window.fetch.__patched = true;
})();