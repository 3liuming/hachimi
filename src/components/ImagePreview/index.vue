<template>
  <el-image
    :src="`${realSrc}`"
    fit="cover"
    :style="`width:${realWidth};height:${realHeight};`"
    :preview-src-list="realSrcList"
  >
    <div slot="error" class="image-slot">
      <i class="el-icon-picture-outline"></i>
    </div>
  </el-image>
</template>

<script>
import { isExternal } from "@/utils/validate"

export default {
  name: "ImagePreview",
  props: {
    src: {
      type: String,
      default: ""
    },
    width: {
      type: [Number, String],
      default: ""
    },
    height: {
      type: [Number, String],
      default: ""
    }
  },
  computed: {
    realSrc() {
      if (!this.src) return;
      let path = this.src.split(',')[0].trim();

      // 如果已经是完整网址，直接返回
      if (isExternal(path)) return path;

      // 去掉开头的 "./" 或 ".\\"，防止路径异常
      path = path.replace(/^(\.\/|\.\\)/, '');

      // 如果路径没以 "/img/" 开头，加上它
      if (!path.startsWith("img/") && !path.startsWith("/img/")) {
        path = "img/" + path;
      }

      // 拼接完整访问地址（环境变量定义了域名）
      const base = process.env.VUE_APP_IMG_DOMAIN || "";
      return `${base}/${path}`.replace(/([^:]\/)\/+/g, "$1"); // 防止双斜杠
    },

    realSrcList() {
      if (!this.src) return [];
      const base = process.env.VUE_APP_IMG_DOMAIN || "";
      return this.src.split(",").map(item => {
        let path = item.trim();
        if (isExternal(path)) return path;
        path = path.replace(/^(\.\/|\.\\)/, '');
        if (!path.startsWith("img/") && !path.startsWith("/img/")) {
          path = "img/" + path;
        }
        return `${base}/${path}`.replace(/([^:]\/)\/+/g, "$1");
      });
    },

    realWidth() {
      return typeof this.width === "string" ? this.width : `${this.width}px`
    },

    realHeight() {
      return typeof this.height === "string" ? this.height : `${this.height}px`
    }
  }
}
</script>


<style lang="scss" scoped>
.el-image {
  border-radius: 5px;
  background-color: #ebeef5;
  box-shadow: 0 0 5px 1px #ccc;
  ::v-deep .el-image__inner {
    transition: all 0.3s;
    cursor: pointer;
    &:hover {
      transform: scale(1.2);
    }
  }
  ::v-deep .image-slot {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    color: #909399;
    font-size: 30px;
  }
}
</style>
