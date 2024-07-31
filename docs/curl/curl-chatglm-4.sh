curl -X POST \
        -H "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiIsInNpZ25fdHlwZSI6IlNJR04ifQ.eyJhcGlfa2V5IjoiZGIzNDg2ZmY1MjQ1OWZiOTJiNzVmYTZkYzhkM2Y2NmUiLCJleHAiOjE3MjI0MzQ1OTE3NDEsInRpbWVzdGFtcCI6MTcyMjQzMjc5MTc0Nn0.K485R8wuPGm6DZTB9IbPrTWj7Vqq02ncNLrHtmmjEHc" \
        -H "Content-Type: application/json" \
        -H "User-Agent: Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)" \
        -d '{
          "model":"glm-4",
          "stream": "true",
          "messages": [
              {
                  "role": "user",
                  "content": "1+1"
              }
          ]
        }' \
  https://open.bigmodel.cn/api/paas/v4/chat/completions
