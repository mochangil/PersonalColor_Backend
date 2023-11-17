from personal_color_analysis import personal_color
import argparse
import os


def main():
    # 인자값 받을 인스턴스 생성
    parser = argparse.ArgumentParser(description = 'Please input your image.')

    # 입력받을 인자값 등록
    parser.add_argument('--image', required = False, help='input .jpg or .png file')
    parser.add_argument('--dir', required = False, help='input image directory')

    # 입력받은 인자값을 args에 저장
    args = parser.parse_args()

    ##################################
    #         a single image         #
    ##################################
    if args.image != None:
        imgpath = args.image
        personal_color.analysis(imgpath)

    ##################################
    #  multiple images in directory  #
    ##################################
    elif args.dir != None:
        dirpath = args.dir
        imgs = os.listdir(dirpath)
        for imgpath in imgs:
            print(os.path.join(dirpath, imgpath))
            personal_color.analysis(os.path.join(dirpath, imgpath))
import resource

# 현재 프로세스의 최대 메모리 사용량을 가져옵니다. (Unix/Linux)
if __name__ == '__main__':
    main()
    ### 아래는 메모리 사용량을 확인하는 코드
    #memory_usage = resource.getrusage(resource.RUSAGE_SELF).ru_maxrss

    #print(f"Memory Usage: {memory_usage / 1024:.2f} MB")  # KB를 MB로 변환