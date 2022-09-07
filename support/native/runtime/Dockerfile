# image used for native run since original image is missing fonts and libfreetype, based on https://buildpacks.io/docs/operator-guide/create-a-stack/
FROM ubuntu:bionic-20220829 as base

# 2. Set required CNB information
ENV CNB_USER_ID=1000
ENV CNB_GROUP_ID=1000
ENV CNB_STACK_ID="io.buildpacks.stacks.bionic"
LABEL io.buildpacks.stack.id="io.buildpacks.stacks.bionic"

# 3. Create the user
RUN groupadd cnb --gid ${CNB_GROUP_ID} && \
  useradd --uid ${CNB_USER_ID} --gid ${CNB_GROUP_ID} -m -s /bin/bash cnb

# 4. Install common packages
RUN apt-get update && \
    apt-get install --assume-yes fontconfig libfreetype6-dev && \
      rm -rf /var/lib/apt/lists/* \

# ========== ADDED ===========
# 5. Start a new run stage
FROM base as run

# 6. Set user and group (as declared in base image)
USER ${CNB_USER_ID}:${CNB_GROUP_ID}
