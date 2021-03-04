module.exports = {
  async rewrites() {
    return [
      {
        source: "/api/:path*",
        destination: "http://localhost:8080/:path*", // Proxy to Backend
      }
    ];
  },
  webpack(config) {
    config.resolve.modules.push(__dirname)
    config.resolve.extensions.push('.ts','.tsx','.native.ts','.native.tsx')
    return config;
  },
};
