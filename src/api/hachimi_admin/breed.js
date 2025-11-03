import request from '@/utils/request'

// 查询宠物品种列表
export function listBreed(query) {
  return request({
    url: '/hachimi_admin/breed/list',
    method: 'get',
    params: query
  })
}

// 查询宠物品种详细
export function getBreed(breedId) {
  return request({
    url: '/hachimi_admin/breed/' + breedId,
    method: 'get'
  })
}

// 新增宠物品种
export function addBreed(data) {
  return request({
    url: '/hachimi_admin/breed',
    method: 'post',
    data: data
  })
}

// 修改宠物品种
export function updateBreed(data) {
  return request({
    url: '/hachimi_admin/breed',
    method: 'put',
    data: data
  })
}

// 删除宠物品种
export function delBreed(breedId) {
  return request({
    url: '/hachimi_admin/breed/' + breedId,
    method: 'delete'
  })
}
