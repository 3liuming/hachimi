import request from '@/utils/request'

// 查询用户（普通用户+管理员）列表
export function listUser(query) {
  return request({
    url: '/hachimiSys/user/list',
    method: 'get',
    params: query
  })
}

// 查询用户（普通用户+管理员）详细
export function getUser(userId) {
  return request({
    url: '/hachimiSys/user/' + userId,
    method: 'get'
  })
}

// 新增用户（普通用户+管理员）
export function addUser(data) {
  return request({
    url: '/hachimiSys/user',
    method: 'post',
    data: data
  })
}

// 修改用户（普通用户+管理员）
export function updateUser(data) {
  return request({
    url: '/hachimiSys/user',
    method: 'put',
    data: data
  })
}

// 删除用户（普通用户+管理员）
export function delUser(userId) {
  return request({
    url: '/hachimiSys/user/' + userId,
    method: 'delete'
  })
}
