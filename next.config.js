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
    return config;
  },
};
