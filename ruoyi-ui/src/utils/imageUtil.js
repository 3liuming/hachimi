/**
 * 图片路径处理工具
 */

/**
 * 将数据库存储的相对路径转换为可访问的完整URL
 * @param {String} relativePath - 数据库中的相对路径,如: ./img/cat/001.jpg
 * @returns {String} 完整的图片URL
 */
export function formatImageUrl(relativePath) {
  if (!relativePath) return '';

  // 移除开头的 ./ 或 ./
  let cleanPath = relativePath.replace(/^\.\/?/, '');

  // 如果路径不是以 / 开头,添加 /
  if (!cleanPath.startsWith('/')) {
    cleanPath = '/' + cleanPath;
  }

  // 拼接后端地址
  // 开发环境
  if (process.env.NODE_ENV === 'development') {
    return `http://localhost:8080${cleanPath}`;
  }

  // 生产环境 - 使用配置的API地址
  const baseUrl = process.env.VUE_APP_BASE_API || 'http://localhost:8080';
  return `${baseUrl}${cleanPath}`;
}

/**
 * 批量处理图片路径
 * @param {Array} list - 包含图片路径的数组
 * @param {String} pathKey - 图片路径的字段名,默认为 'imagePath'
 * @returns {Array} 处理后的数组
 */
export function formatImageList(list, pathKey = 'imagePath') {
  if (!Array.isArray(list)) return [];

  return list.map(item => {
    if (item[pathKey]) {
      return {
        ...item,
        [pathKey]: formatImageUrl(item[pathKey]),
        originalPath: item[pathKey] // 保留原始路径
      };
    }
    return item;
  });
}
