import request from '@/utils/request'

// 查询宠物品种信息列表
export function listBreed(query) {
  return request({
    url: '/hachimiSys/breed/list',
    method: 'get',
    params: query
  })
}

// 查询宠物品种信息详细
export function getBreed(breedId) {
  return request({
    url: '/hachimiSys/breed/' + breedId,
    method: 'get'
  })
}

// 新增宠物品种信息
export function addBreed(data) {
  return request({
    url: '/hachimiSys/breed',
    method: 'post',
    data: data
  })
}

// 修改宠物品种信息
export function updateBreed(data) {
  return request({
    url: '/hachimiSys/breed',
    method: 'put',
    data: data
  })
}

// 删除宠物品种信息
export function delBreed(breedId) {
  return request({
    url: '/hachimiSys/breed/' + breedId,
    method: 'delete'
  })
}
