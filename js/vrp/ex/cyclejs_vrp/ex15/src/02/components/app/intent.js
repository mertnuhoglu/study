import xs from 'xstream';

export default function intent(dom$) {
  const request$ = xs.of(
    {
      url: 'http://localhost:8080/rest/plan?select=plan_id,usr,depot_id',
      method: 'GET',
      headers: {
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJyb2xlIjoid2VidXNlciJ9.uSsS2cukBlM6QXe4Y0H90fsdkJSGcle9b7p_kMV1Ymk"
      }
    }
  );
  return request$;
}

